package com.api.resume.adapter.controller;

import com.api.resume.adapter.converter.ResumeReviewResponseConverter;
import com.api.resume.adapter.payload.resumereivew.*;
import com.api.resume.adapter.proxy.ResumeReviewProxy;
import com.api.resume.application.service.query.ResumeReviewListQuery;
import com.api.resume.domain.dto.ResumeReviewDetailDto;
import com.api.resume.domain.dto.ResumeReviewListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/resumes/reviews")
@RequiredArgsConstructor
public class ResumeReviewController {

    private final ResumeReviewProxy resumeReviewProxy;

    @GetMapping("")
    public ResponseEntity<List<ResumeReviewListResponse>> getAllResumeReview(ResumeReviewListRequest request,
                                                                             @RequestParam(defaultValue = "DESC") String direction) {
        ResumeReviewListQuery query =
                ResumeReviewListQuery.builder()
                        .title(request.getTitle())
                        .companyName(request.getCompanyName())
                        .keyword(request.getKeyword())
                        .build();
        List<ResumeReviewListDto> resumeReviewList = resumeReviewProxy.getResumeReviewList(query, direction);
        List<ResumeReviewListResponse> results = ResumeReviewResponseConverter.INSTANCE.toResumeReviewListResponses(resumeReviewList);
        return ResponseEntity.ok().body(results);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ResumeReviewDetailResponse> getResumeReview(@PathVariable Long reviewId) {
        ResumeReviewDetailDto resumeReviewDetail = resumeReviewProxy.getResumeReview(reviewId);
        ResumeReviewDetailResponse result = ResumeReviewResponseConverter.INSTANCE.toResumeReviewDetailResponse(resumeReviewDetail);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody ResumeReviewCreateRequest request) {
        URI uri = URI.create("");
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Void> update(@PathVariable Long reviewId, @RequestBody ResumeReviewUpdateRequest request) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> delete(@PathVariable Long reviewId) {
        return ResponseEntity.noContent().build();
    }
}
