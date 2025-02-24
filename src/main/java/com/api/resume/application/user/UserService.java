package com.api.resume.application.user;

import com.api.resume.adapter.persistence.user.UserRepository;
import com.api.resume.application.user.command.UserCreateCommand;
import com.api.resume.application.user.command.UserUpdateCommand;
import com.api.resume.domain.dto.UserDetailDto;
import com.api.resume.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetailDto getUser(long userId) {
        User user = userRepository.findById(userId);
        return UserDetailDto.from(user);
    }

    @Transactional
    @Override
    public long create(UserCreateCommand command) {
        return userRepository.save(User.create(command)).getId();
    }

    @Transactional
    @Override
    public long update(UserUpdateCommand command) {
        User user = userRepository.findById(command.userId());
        user.update(command);
        return user.getId();
    }
}
