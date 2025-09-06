package com.internintelligence.gmailapplication.model.dto;

import com.internintelligence.gmailapplication.model.enums.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TranslationDto {
    public record TranslateRequest(@NotNull Language source,
                                   @NotNull Language target,
                                   @NotBlank String text,
                                   String userEmail){ }

    public record VoiceTranslateRequest(@NotNull Language source,
                                        @NotNull Language target,
                                        @NotBlank String speechText,
                                        String userEmail){ }


    public record SaveRequest(Long translationId, boolean saved){ }
}
