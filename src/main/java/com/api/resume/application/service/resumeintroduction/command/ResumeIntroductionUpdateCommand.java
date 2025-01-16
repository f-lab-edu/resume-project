package com.api.resume.application.service.resumeintroduction.command;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResumeIntroductionUpdateCommand {
    long resumeIntroductionId;
    String title;
    String content;
}
