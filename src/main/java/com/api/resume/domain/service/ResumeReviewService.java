package com.api.resume.domain.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ResumeReviewService {

    public void validateUser(final long userId) {
        // userId가 없는 경우 에외처리
    }

    public void validDate(final LocalDate projectStartDate, final LocalDate projectEndDate) {
        if (projectStartDate.isAfter(projectEndDate)) {
            throw new IllegalArgumentException("시작 날짜는 종료 날짜 이후일 수 없습니다.");
        }
    }
}
