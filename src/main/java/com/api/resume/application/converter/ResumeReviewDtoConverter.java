package com.api.resume.application.converter;

import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import com.api.resume.domain.entity.ResumeReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResumeReviewDtoConverter {

    ResumeReviewDtoConverter INSTANCE = Mappers.getMapper(ResumeReviewDtoConverter.class);

    @Mapping(source = "id", target = "reviewId")
    ResumeReviewDetailDto toResumeReviewDetailDto(ResumeReview resumeReview);

    @Mapping(source = "id", target = "reviewId")
    ResumeReviewListDto toResumeReviewListDto(ResumeReview resumeReview);

    List<ResumeReviewListDto> toResumeReviewListDtos(List<ResumeReview> resumeReviews);

}
