package com.api.resume.application.service.user;

import com.api.resume.adapter.persistence.user.UserAdapter;
import com.api.resume.application.usecase.user.UserDetailUseCase;
import com.api.resume.domain.dto.UserDetailDto;
import com.api.resume.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryService implements UserDetailUseCase {

    private final UserAdapter userAdapter;

    @Override
    public UserDetailDto getUser(long userId) {
        User user = userAdapter.findById(userId);
        return UserDetailDto.from(user);
    }
}
