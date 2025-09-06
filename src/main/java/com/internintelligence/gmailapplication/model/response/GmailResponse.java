package com.internintelligence.gmailapplication.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GmailResponse {
    private Long id;
    private String from;
    private String to;
    private String subject;
    private String snippet;
    private LocalDateTime receivedAt;
    private boolean starred;
    private boolean read;
}
