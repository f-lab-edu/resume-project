package com.api.resume.adapter.controller;

import com.api.resume.adapter.payload.user.*;
import com.api.resume.application.user.UserUseCase;
import com.api.resume.application.user.command.UserCreateCommand;
import com.api.resume.application.user.command.UserUpdateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @GetMapping("/{userId}")
    public UserDetailResponse getUser(@PathVariable long userId) {
        return UserDetailResponse.from(userUseCase.getUser(userId));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreateResponse create(@RequestBody UserCreateRequest request) {
        UserCreateCommand create = UserCreateCommand.builder()
                .name(request.name())
                .email(request.email())
                .birthDate(request.birthDate())
                .phoneNumber(request.phoneNumber())
                .build();

        return UserCreateResponse.from(userUseCase.create(create));
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserUpdateResponse update(@PathVariable long userId,
                                     @RequestBody UserUpdateRequest request) {
        UserUpdateCommand update = UserUpdateCommand.builder()
                .userId(userId)
                .name(request.name())
                .email(request.email())
                .birthDate(request.birthDate())
                .phoneNumber(request.phoneNumber())
                .build();

        return UserUpdateResponse.from(userUseCase.update(update));
    }


}
