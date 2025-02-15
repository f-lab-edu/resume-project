package com.api.resume.application.resumeintroduction.command;

import lombok.Builder;

@Builder
public record ResumeIntroductionCreateCommand(String title, String content) {

}
