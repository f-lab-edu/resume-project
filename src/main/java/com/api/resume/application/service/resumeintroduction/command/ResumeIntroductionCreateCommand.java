package com.api.resume.application.service.resumeintroduction.command;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResumeIntroductionCreateCommand {
    String title;
    String content;
}
