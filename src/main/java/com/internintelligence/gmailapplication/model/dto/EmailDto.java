package com.internintelligence.gmailapplication.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    private Long id;
    private String from;
    private String subject;
    private String snippet;
    private LocalDateTime receivedAt;
    private boolean starred;
    private boolean read;

}
