package com.api.resume.adapter.controller;

import com.api.resume.application.userlicense.UserLicenseUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserLicenseController.class)
public class UserLicenseControllerTest {

    private static final String URI = "/api/v1/users/{userId}/licenses";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private UserLicenseUseCase userLicenseUseCase;


}
