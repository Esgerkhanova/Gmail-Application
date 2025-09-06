package com.internintelligence.gmailapplication.model.dto;

import com.internintelligence.gmailapplication.model.enums.Language;

public class AccountDto {
    public record UpsertSettings(Language defaultSource, Language defaultTarget, Boolean darkMode, String userEmail){}
}
