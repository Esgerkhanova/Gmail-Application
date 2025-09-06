package com.internintelligence.gmailapplication.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GmailDto {
    public record ComposeRequest(
            @Email @NotBlank String from,
            @Email @NotBlank String to,
            @Size(max=255) String subject,
            @NotBlank String body
    ){}


    public record EmailSummary(
            Long id,
            String from,
            String subject,
            String preview,
            boolean starred,
            String receivedAt
    ){}
}