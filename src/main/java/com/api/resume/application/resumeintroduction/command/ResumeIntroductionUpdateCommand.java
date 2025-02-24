package com.api.resume.application.resumeintroduction.command;

import lombok.Builder;

@Builder
public record ResumeIntroductionUpdateCommand(long resumeIntroductionId, String title, String content) {
}
