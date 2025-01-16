package com.api.resume.adapter.controller;

import com.api.resume.adapter.payload.resumereivew.*;
import com.api.resume.application.service.resumereview.command.ResumeReviewCreateCommand;
import com.api.resume.application.service.resumereview.command.ResumeReviewUpdateCommand;
import com.api.resume.application.service.resumereview.query.ResumeReviewListQuery;
import com.api.resume.application.usecase.resumereview.*;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resumes/reviews")
@RequiredArgsConstructor
public class ResumeReviewController {

    private final ResumeReviewListUseCase resumeReviewListUseCase;
    private final ResumeReviewDetailUseCase resumeReviewDetailUseCase;
    private final ResumeReviewCreateUseCase resumeReviewCreateUseCase;
    private final ResumeReviewUpdateUseCase resumeReviewUpdateUseCase;
    private final ResumeReviewDeleteUseCase resumeReviewDeleteUseCase;


    @GetMapping("")
    public List<ResumeReviewListResponse> getAllResumeReview(ResumeReviewListRequest request,
                                                                             @RequestParam(defaultValue = "DESC") String direction) {
        ResumeReviewListQuery query =
                ResumeReviewListQuery.builder()
                        .title(request.getTitle())
                        .companyName(request.getCompanyName())
                        .keyword(request.getKeyword())
                        .build();
        List<ResumeReviewListDto> resumeReviewList = resumeReviewListUseCase.getAllResumeReviewList(query, direction);
        return ResumeReviewListResponse.from(resumeReviewList);
    }

    @GetMapping("/{reviewId}")
    public ResumeReviewDetailResponse getResumeReview(@PathVariable Long reviewId) {
        ResumeReviewDetailDto resumeReviewDetail = resumeReviewDetailUseCase.getResumeReview(reviewId);
        return ResumeReviewDetailResponse.from(resumeReviewDetail);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ResumeReviewCreateRequest request) {
        ResumeReviewCreateCommand command = ResumeReviewCreateCommand.builder()
                .title(request.getTitle())
                .companyName(request.getCompanyName())
                .situation(request.getSituation())
                .task(request.getTask())
                .actionsTaken(request.getActionsTaken())
                .result(request.getResult())
                .keywords(request.getKeywords())
                .projectStartDate(request.getProjectStartDate())
                .projectEndDate(request.getProjectEndDate())
                .build();

        resumeReviewCreateUseCase.create(command);
    }

    @PutMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long reviewId, @RequestBody ResumeReviewUpdateRequest request) {
        ResumeReviewUpdateCommand command = ResumeReviewUpdateCommand.builder()
                .userId(request.getUserId())
                .reviewId(reviewId)
                .title(request.getTitle())
                .companyName(request.getCompanyName())
                .situation(request.getSituation())
                .task(request.getTask())
                .actionsTaken(request.getActionsTaken())
                .result(request.getResult())
                .keywords(request.getKeywords())
                .projectStartDate(request.getProjectStartDate())
                .projectEndDate(request.getProjectEndDate())
                .build();
        resumeReviewUpdateUseCase.update(command);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long reviewId) {
        resumeReviewDeleteUseCase.delete(reviewId);
    }
}
