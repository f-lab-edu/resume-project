package com.api.resume.application.resumeintroduction.command;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResumeIntroductionUpdateCommand {
    long resumeIntroductionId;
    String title;
    String content;
}
