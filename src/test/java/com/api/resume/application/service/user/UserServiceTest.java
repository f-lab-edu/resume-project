package com.api.resume.application.service.user;

import com.api.resume.adapter.persistence.user.UserRepository;
import com.api.resume.application.user.UserService;
import com.api.resume.application.user.command.UserCreateCommand;
import com.api.resume.application.user.command.UserUpdateCommand;
import com.api.resume.domain.dto.UserDetailDto;
import com.api.resume.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john@example.com")
                .birthDate(LocalDate.of(1990, 1, 1))
                .phoneNumber("010-1234-5678")
                .build();
    }

    @Test
    @DisplayName("사용자 단건 조회")
    void getUser() {
        // Given
        given(userRepository.findById(1L)).willReturn(user);

        // When
        UserDetailDto result = userService.getUser(1L);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.name()).isEqualTo("John Doe");
        assertThat(result.email()).isEqualTo("john@example.com");
    }

    @Test
    @DisplayName("사용자 생성")
    void createUser() {
        // Given
        UserCreateCommand command = new UserCreateCommand("John Doe",
                "john@example.com",
                "010-1234-5678",
                LocalDate.of(1990, 1, 1));

        User savedUser = User.create(command);

        given(userRepository.save(any(User.class))).willReturn(savedUser);

        // When
        long userId = userService.create(command);

        // Then
        assertThat(userId).isEqualTo(1L);
    }

    @Test
    @DisplayName("사용자 수정")
    void updateUser() {
        // Given
        UserUpdateCommand command = new UserUpdateCommand(1L,
                "updated@example.com",
                "Updated Name",
                "010-9876-5432",
                LocalDate.of(1992, 2, 2)
        );

        given(userRepository.findById(1L)).willReturn(user);

        // When
        long updatedId = userService.update(command);

        // Then
        assertThat(updatedId).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("Updated Name");
        assertThat(user.getEmail()).isEqualTo("updated@example.com");
    }

}