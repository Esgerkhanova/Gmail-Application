package com.internintelligence.gmailapplication.service;

import com.internintelligence.gmailapplication.model.dto.AccountDto;
import com.internintelligence.gmailapplication.model.entity.AppSettings;
import com.internintelligence.gmailapplication.repository.AppSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AppSettingsRepository repo;


    public AppSettings get(String user){
        return repo.findByUserEmail(defaultUser(user)).orElseGet(() -> repo.save(AppSettings.builder().userEmail(defaultUser(user)).build()));
    }


    public AppSettings upsert(AccountDto.UpsertSettings dto){
        AppSettings s = get(dto.userEmail());
        if(dto.defaultSource()!=null) s.setDefaultSource(dto.defaultSource());
        if(dto.defaultTarget()!=null) s.setDefaultTarget(dto.defaultTarget());
        if(dto.darkMode()!=null) s.setDarkMode(dto.darkMode());
        return repo.save(s);
    }


    private String defaultUser(String email){ return email == null || email.isBlank() ? "me@example.com" : email; }

}
