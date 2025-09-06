package com.internintelligence.gmailapplication.controller;

import com.internintelligence.gmailapplication.model.dto.AccountDto;
import com.internintelligence.gmailapplication.model.entity.AppSettings;
import com.internintelligence.gmailapplication.model.response.ApiResponse;
import com.internintelligence.gmailapplication.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;



    @GetMapping
    public ApiResponse<AppSettings> get(@RequestParam(defaultValue = "me@example.com") String user){
        return ApiResponse.ok(service.get(user));
    }


    @PatchMapping
    public ApiResponse<AppSettings> patch(@RequestBody AccountDto.UpsertSettings dto){
        return ApiResponse.ok(service.upsert(dto));
    }
}
