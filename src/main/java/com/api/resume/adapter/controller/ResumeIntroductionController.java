package com.api.resume.adapter.controller;

import com.api.resume.adapter.payload.resumeintroduction.ResumeIntroductionCreateRequest;
import com.api.resume.adapter.payload.resumeintroduction.ResumeIntroductionDetailResponse;
import com.api.resume.adapter.payload.resumeintroduction.ResumeIntroductionListResponse;
import com.api.resume.adapter.payload.resumeintroduction.ResumeIntroductionUpdateRequest;
import com.api.resume.application.service.resumeintroduction.command.ResumeIntroductionCreateCommand;
import com.api.resume.application.service.resumeintroduction.command.ResumeIntroductionUpdateCommand;
import com.api.resume.application.service.resumeintroduction.query.ResumeIntroductionListQuery;
import com.api.resume.application.usecase.resumeintroduction.*;
import com.api.resume.domain.dto.ResumeIntroductionDetailDto;
import com.api.resume.domain.dto.ResumeIntroductionListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resume-introduction")
@RequiredArgsConstructor
public class ResumeIntroductionController {

    private final ResumeIntroductionListUseCase resumeIntroductionListUseCase;
    private final ResumeIntroductionDetailUseCase resumeIntroductionDetailUseCase;
    private final ResumeIntroductionCreateUseCase resumeIntroductionCreateUseCase;
    private final ResumeIntroductionUpdateUseCase resumeIntroductionUpdateUseCase;
    private final ResumeIntroductionDeleteUseCase resumeIntroductionDeleteUseCase;

    @GetMapping("/")
    public List<ResumeIntroductionListResponse> getAllResumeIntroductionList() {
        ResumeIntroductionListQuery query = new ResumeIntroductionListQuery();
        List<ResumeIntroductionListDto> results =
                resumeIntroductionListUseCase.getAll(query);

        return ResumeIntroductionListResponse.from(results);
    }

    @GetMapping("/{resumeIntroductionId}")
    public ResumeIntroductionDetailResponse getResumeIntroduction(@PathVariable long resumeIntroductionId) {
        ResumeIntroductionDetailDto result =
                resumeIntroductionDetailUseCase.getResumeIntroduction(resumeIntroductionId);

        return ResumeIntroductionDetailResponse.from(result);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ResumeIntroductionCreateRequest request) {

        ResumeIntroductionCreateCommand command = ResumeIntroductionCreateCommand.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        resumeIntroductionCreateUseCase.create(command);
    }

    @PutMapping("/{resumeIntroductionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long resumeIntroductionId,
                       @RequestBody ResumeIntroductionUpdateRequest request) {
        ResumeIntroductionUpdateCommand command = ResumeIntroductionUpdateCommand.builder()
                .resumeIntroductionId(resumeIntroductionId)
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        resumeIntroductionUpdateUseCase.update(command);
    }

    @DeleteMapping("/{resumeIntroductionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long resumeIntroductionId) {
        resumeIntroductionDeleteUseCase.delete(resumeIntroductionId);
    }

}