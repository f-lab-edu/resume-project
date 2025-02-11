package com.api.resume.adapter.controller;

import com.api.resume.adapter.payload.userlicense.UserLicenseCreateRequest;
import com.api.resume.adapter.payload.userlicense.UserLicenseDetailResponse;
import com.api.resume.adapter.payload.userlicense.UserLicenseListResponse;
import com.api.resume.application.userlicense.UserLicenseUseCase;
import com.api.resume.application.userlicense.query.UserLicenseDetailQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/licenses")
@RequiredArgsConstructor
public class UserLicenseController {

    private final UserLicenseUseCase userLicenseUseCase;

    @GetMapping("")
    public List<UserLicenseListResponse> getLicenses(@PathVariable long userId) {
        return UserLicenseListResponse.from(userLicenseUseCase.getAllUserLicenses(userId));
    }

    @GetMapping("/{licenseId}")
    public UserLicenseDetailResponse getLicense(@PathVariable long userId, @PathVariable long licenseId) {
        UserLicenseDetailQuery query = UserLicenseDetailQuery.of(userId, licenseId);
        return UserLicenseDetailResponse.from(userLicenseUseCase.getUserLicense(query));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLicense(@RequestBody UserLicenseCreateRequest request) {
        // TODO: implement

    }


}
