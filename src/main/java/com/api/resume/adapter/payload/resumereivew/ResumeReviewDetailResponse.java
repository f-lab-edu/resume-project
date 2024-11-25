package com.api.resume.adapter.payload.resumereivew;

import com.api.resume.domain.dto.ResumeReviewDetailDto;
import lombok.Value;
import java.time.LocalDate;

@Value
public class ResumeReviewDetailResponse {
    Long reviewId;
    String title;
    String companyName;
    LocalDate projectStartDate;
    LocalDate projectEndDate;
    String keywords;
    String situation;
    String task;
    String actionsTaken;
    String result;

    public static ResumeReviewDetailResponse from(final ResumeReviewDetailDto dto) {
        return new ResumeReviewDetailResponse(dto.getReviewId()
                                        , dto.getTitle()
                                        , dto.getCompanyName()
                                        , dto.getProjectStartDate()
                                        , dto.getProjectEndDate()
                                        , dto.getKeywords()
                                        , dto.getSituation()
                                        , dto.getTask()
                                        , dto.getActionsTaken()
                                        , dto.getResult());
    }
}
