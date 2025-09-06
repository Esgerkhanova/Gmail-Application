package com.internintelligence.gmailapplication.controller;

import com.internintelligence.gmailapplication.model.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/drawer")
public class DrawerController {
    @GetMapping
    public ApiResponse<List<String>> menu(){
        return ApiResponse.ok(List.of("Inbox", "Starred", "Sent", "Drafts", "Spam", "Trash"));
    }
}
