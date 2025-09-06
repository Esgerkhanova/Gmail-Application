package com.internintelligence.gmailapplication.controller;

import com.internintelligence.gmailapplication.model.enums.Language;
import com.internintelligence.gmailapplication.model.dto.TranslationDto;
import com.internintelligence.gmailapplication.model.response.ApiResponse;
import com.internintelligence.gmailapplication.model.entity.Translation;
import com.internintelligence.gmailapplication.service.TranslateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/translate")
@RequiredArgsConstructor
public class TranslateController {
    private final TranslateService service;


    @PostMapping
    public ApiResponse<Translation> translate(@Valid @RequestBody TranslationDto.TranslateRequest req){
        return ApiResponse.ok(service.translate(req));
    }



    @PostMapping("/voice")
    public ApiResponse<Translation> voice(@Valid @RequestBody TranslationDto.VoiceTranslateRequest req){
        return ApiResponse.ok(service.voiceTranslate(req));
    }



    @GetMapping("/history")
    public ApiResponse<Page<Translation>> history(@RequestParam(defaultValue = "me@example.com") String user,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "20") int size){
        return ApiResponse.ok(service.history(user, PageRequest.of(page, size)));
    }



    @GetMapping("/saved")
    public ApiResponse<Page<Translation>> saved(@RequestParam(defaultValue = "me@example.com") String user,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "20") int size){
        return ApiResponse.ok(service.saved(user, PageRequest.of(page, size)));
    }


    @PostMapping("/saved")
    public ApiResponse<Translation> save(@RequestBody TranslationDto.SaveRequest req){
        return ApiResponse.ok(service.updateSaved(req));
    }



    @GetMapping("/languages")
    public ApiResponse<Language[]> languages(){
        return ApiResponse.ok(service.supported());
    }
}

