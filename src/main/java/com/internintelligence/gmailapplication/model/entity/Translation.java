package com.internintelligence.gmailapplication.model.entity;

import com.internintelligence.gmailapplication.model.enums.Language;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Language source;
    @Enumerated(EnumType.STRING)
    private Language target;
    @Lob
    private String inputText;
    @Lob
    private String outputText;
    private OffsetDateTime createdAt;
    private String userEmail;
    private boolean saved;
}
