package com.api.resume.adapter.converter;

import com.api.resume.adapter.payload.resumereivew.ResumeReviewDetailResponse;
import com.api.resume.adapter.payload.resumereivew.ResumeReviewListResponse;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResumeReviewResponseConverter {
    ResumeReviewResponseConverter INSTANCE = Mappers.getMapper(ResumeReviewResponseConverter.class);

    List<ResumeReviewListResponse> toResumeReviewListResponses(List<ResumeReviewListDto> resumeReviewListDtos);
    ResumeReviewDetailResponse toResumeReviewDetailResponse(ResumeReviewDetailDto resumeReviewDetailDto);
}
