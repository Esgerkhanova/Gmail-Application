package com.internintelligence.gmailapplication.service;

import com.internintelligence.gmailapplication.SimpleDictionaryEngine;
import com.internintelligence.gmailapplication.model.enums.Language;
import com.internintelligence.gmailapplication.model.dto.TranslationDto;
import com.internintelligence.gmailapplication.model.entity.Translation;
import com.internintelligence.gmailapplication.repository.TranslationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class TranslateService {
    private final TranslationRepository repo;
    private final SimpleDictionaryEngine engine;


    public Translation translate(TranslationDto.TranslateRequest req){
        String out = engine.translate(req.source(), req.target(), req.text());
        Translation t = Translation.builder()
                .source(req.source()).target(req.target())
                .inputText(req.text()).outputText(out)
                .userEmail(defaultUser(req.userEmail()))
                .createdAt(OffsetDateTime.now())
                .saved(false)
                .build();
        return repo.save(t);
    }


    public Translation voiceTranslate(TranslationDto.VoiceTranslateRequest req){
// In real app, speech-to-text would happen before calling this endpoint
        String out = engine.translate(req.source(), req.target(), req.speechText());
        Translation t = Translation.builder()
                .source(req.source()).target(req.target())
                .inputText(req.speechText()).outputText(out)
                .userEmail(defaultUser(req.userEmail()))
                .createdAt(OffsetDateTime.now())
                .saved(false)
                .build();
        return repo.save(t);
    }


    public Page<Translation> history(String userEmail, Pageable pageable){
        return repo.findByUserEmailOrderByCreatedAtDesc(defaultUser(userEmail), pageable);
    }


    public Page<Translation> saved(String userEmail, Pageable pageable){
        return repo.findByUserEmailAndSavedTrueOrderByCreatedAtDesc(defaultUser(userEmail), pageable);
    }


    public Translation updateSaved(TranslationDto.SaveRequest req){
        Translation t = repo.findById(Math.toIntExact(req.translationId()))
                .orElseThrow(() -> new IllegalArgumentException("Translation not found"));
        t.setSaved(req.saved());
        return repo.save(t);
    }


    public Language[] supported(){ return Language.values(); }


    private String defaultUser(String email){ return email == null || email.isBlank() ? "me@example.com" : email; }

}
