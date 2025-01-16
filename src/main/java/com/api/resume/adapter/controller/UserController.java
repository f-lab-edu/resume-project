package com.api.resume.adapter.controller;

import com.api.resume.adapter.payload.user.UserCreateRequest;
import com.api.resume.adapter.payload.user.UserDetailResponse;
import com.api.resume.adapter.payload.user.UserUpdateRequest;
import com.api.resume.application.service.user.command.UserCreateCommand;
import com.api.resume.application.service.user.command.UserUpdateCommand;
import com.api.resume.application.usecase.user.UserCreateUseCase;
import com.api.resume.application.usecase.user.UserDetailUseCase;
import com.api.resume.application.usecase.user.UserUpdateUseCase;
import com.api.resume.domain.dto.UserDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailUseCase userDetailUseCase;
    private final UserCreateUseCase userCreateUseCase;
    private final UserUpdateUseCase userUpdateUseCase;

    @GetMapping("/{userId}")
    public UserDetailResponse getUser(@PathVariable long userId) {
        UserDetailDto userDetailDto = userDetailUseCase.getUser(userId);
        return UserDetailResponse.from(userDetailDto);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserCreateRequest request) {
        UserCreateCommand create = UserCreateCommand.builder()
                .name(request.getName())
                .email(request.getEmail())
                .birthDate(request.getBirthDate())
                .phoneNumber(request.getPhoneNumber())
                .build();
        userCreateUseCase.create(create);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long userId,
                       @RequestBody UserUpdateRequest request) {
        UserUpdateCommand update = UserUpdateCommand.builder()
                .userId(userId)
                .name(request.getName())
                .email(request.getEmail())
                .birthDate(request.getBirthDate())
                .phoneNumber(request.getPhoneNumber())
                .build();
        userUpdateUseCase.update(update);
    }

}
