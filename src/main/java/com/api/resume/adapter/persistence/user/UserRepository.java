package com.api.resume.adapter.persistence.user;


import com.api.resume.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepository {

    private final UserJpaRepository userJpaRepository;

    public User findById(final long userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보가 없습니다"));
    }

    public User save(final User user) {
       return userJpaRepository.save(user);
    }
}
