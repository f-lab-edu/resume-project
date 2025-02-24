package com.api.resume.adapter.controller;

import com.api.resume.adapter.payload.user.*;
import com.api.resume.application.user.UserUseCase;
import com.api.resume.application.user.command.UserCreateCommand;
import com.api.resume.application.user.command.UserUpdateCommand;
import com.api.resume.domain.dto.UserDetailDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserUseCase userUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("사용자 단건 조회")
    void getUser() throws Exception {
        // Given
        UserDetailDto userDetailDto = UserDetailDto.builder()
                .userId(1L)
                .email("john@example.com")
                .name("John Doe")
                .phoneNumber("010-1234-5678")
                .birthDate(LocalDate.of(1990, 1, 1))
                .build();

        UserDetailResponse response = UserDetailResponse.from(userDetailDto);

        given(userUseCase.getUser(1L)).willReturn(userDetailDto);

        // When & Then
        mockMvc.perform(get("/api/v1/users/{userId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    @DisplayName("사용자 생성")
    void createUser() throws Exception {
        // Given
        UserCreateRequest request = new UserCreateRequest(
                "john@example.com",
                "John Doe",
                "010-1234-5678",
                LocalDate.of(1990, 1, 1));

        UserCreateResponse response = UserCreateResponse.from(1L);

        given(userUseCase.create(any(UserCreateCommand.class))).willReturn(response.userId());

        // When & Then
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(1L));
    }

    @Test
    @DisplayName("사용자 수정")
    void updateUser() throws Exception {
        // Given
        UserUpdateRequest request = new UserUpdateRequest(
                "john@example.com",
                "John Doe",
                "010-1234-5678",
                LocalDate.of(1990, 1, 1));

        UserUpdateResponse response = new UserUpdateResponse(1L);

        given(userUseCase.update(any(UserUpdateCommand.class))).willReturn(response.userId());

        // When & Then
        mockMvc.perform(put("/api/v1/users/{userId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L));
    }
}