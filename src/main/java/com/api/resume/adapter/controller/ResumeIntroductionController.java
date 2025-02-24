package com.api.resume.adapter.controller;

import com.api.resume.adapter.payload.resumeintroduction.*;
import com.api.resume.application.resumeintroduction.ResumeIntroductionUseCase;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionCreateCommand;
import com.api.resume.application.resumeintroduction.command.ResumeIntroductionUpdateCommand;
import com.api.resume.application.resumeintroduction.query.ResumeIntroductionListQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resume-introduction")
@RequiredArgsConstructor
public class ResumeIntroductionController {

    private final ResumeIntroductionUseCase resumeIntroductionUseCase;

    @GetMapping("/")
    public List<ResumeIntroductionListResponse> getAllResumeIntroductionList() {
        ResumeIntroductionListQuery query = new ResumeIntroductionListQuery();
        return ResumeIntroductionListResponse.from(resumeIntroductionUseCase.getAll(query));
    }

    @GetMapping("/{resumeIntroductionId}")
    public ResumeIntroductionDetailResponse getResumeIntroduction(@PathVariable long resumeIntroductionId) {
        return ResumeIntroductionDetailResponse
                .from(resumeIntroductionUseCase.getResumeIntroduction(resumeIntroductionId));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResumeIntroductionCreateResponse create(@RequestBody ResumeIntroductionCreateRequest request) {
        ResumeIntroductionCreateCommand command = ResumeIntroductionCreateCommand.builder()
                .title(request.title())
                .content(request.content())
                .build();
        return ResumeIntroductionCreateResponse.from(resumeIntroductionUseCase.create(command));
    }

    @PutMapping("/{resumeIntroductionId}")
    @ResponseStatus(HttpStatus.OK)
    public ResumeIntroductionUpdateResponse update(@PathVariable long resumeIntroductionId,
                                                   @RequestBody ResumeIntroductionUpdateRequest request) {
        ResumeIntroductionUpdateCommand command = ResumeIntroductionUpdateCommand.builder()
                .resumeIntroductionId(resumeIntroductionId)
                .title(request.title())
                .content(request.content())
                .build();

        return ResumeIntroductionUpdateResponse.from(resumeIntroductionUseCase.update(command));
    }

    @DeleteMapping("/{resumeIntroductionId}")
    @ResponseStatus(HttpStatus.OK)
    public long delete(@PathVariable long resumeIntroductionId) {
        return resumeIntroductionUseCase.delete(resumeIntroductionId);
    }

}