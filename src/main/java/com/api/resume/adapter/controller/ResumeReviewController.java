package com.api.resume.adapter.controller;

import com.api.resume.adapter.payload.resumereivew.*;
import com.api.resume.application.resumereview.ResumeReviewUseCase;
import com.api.resume.application.resumereview.command.ResumeReviewCreateCommand;
import com.api.resume.application.resumereview.command.ResumeReviewUpdateCommand;
import com.api.resume.application.resumereview.query.ResumeReviewListQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resumes/reviews")
@RequiredArgsConstructor
public class ResumeReviewController {

    private final ResumeReviewUseCase resumeReviewUseCase;

    @GetMapping("")
    public List<ResumeReviewListResponse> getAllResumeReview(ResumeReviewListRequest request,
                                                             @RequestParam(defaultValue = "DESC") String direction) {
        ResumeReviewListQuery query =
                ResumeReviewListQuery.builder()
                        .title(request.title())
                        .companyName(request.companyName())
                        .keyword(request.keyword())
                        .build();
        return ResumeReviewListResponse.from(resumeReviewUseCase.getAllResumeReviewList(query, direction));
    }

    @GetMapping("/{reviewId}")
    public ResumeReviewDetailResponse getResumeReview(@PathVariable Long reviewId) {
        return ResumeReviewDetailResponse.from(resumeReviewUseCase.getResumeReview(reviewId));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResumeReviewCreateResponse create(@RequestBody ResumeReviewCreateRequest request) {
        ResumeReviewCreateCommand command = ResumeReviewCreateCommand.builder()
                .title(request.title())
                .companyName(request.companyName())
                .situation(request.situation())
                .task(request.task())
                .actionsTaken(request.actionsTaken())
                .result(request.result())
                .keywords(request.keywords())
                .projectStartDate(request.projectStartDate())
                .projectEndDate(request.projectEndDate())
                .build();

        return ResumeReviewCreateResponse.from(resumeReviewUseCase.create(command));
    }

    @PutMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public ResumeReviewUpdateResponse update(@PathVariable Long reviewId, @RequestBody ResumeReviewUpdateRequest request) {
        ResumeReviewUpdateCommand command = ResumeReviewUpdateCommand.builder()
                .userId(request.userId())
                .reviewId(reviewId)
                .title(request.title())
                .companyName(request.companyName())
                .situation(request.situation())
                .task(request.task())
                .actionsTaken(request.actionsTaken())
                .result(request.result())
                .keywords(request.keywords())
                .projectStartDate(request.projectStartDate())
                .projectEndDate(request.projectEndDate())
                .build();
        return ResumeReviewUpdateResponse.from(resumeReviewUseCase.update(command));
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public long delete(@PathVariable Long reviewId) {
        return resumeReviewUseCase.delete(reviewId);
    }
}
