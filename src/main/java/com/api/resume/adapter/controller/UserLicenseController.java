package com.api.resume.adapter.controller;

import com.api.resume.adapter.payload.userlicense.UserLicenseCreateRequest;
import com.api.resume.adapter.payload.userlicense.UserLicenseListResponse;
import com.api.resume.application.userlicense.UserLicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/licenses")
@RequiredArgsConstructor
public class UserLicenseController {

    private final UserLicenseService userLicenseService;

    @GetMapping("")
    public List<UserLicenseListResponse> getLicenses(@PathVariable long userId) {
        return UserLicenseListResponse.from(userLicenseService.getAllUserLicenses(userId));
    }

    @GetMapping("/{licenseId}")
    public void getLicense(@PathVariable long userId, @PathVariable long licenseId) {

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLicense(@RequestBody UserLicenseCreateRequest request) {
        // TODO: implement
    }


}
