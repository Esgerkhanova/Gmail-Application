package com.internintelligence.gmailapplication.model.entity;


import com.internintelligence.gmailapplication.model.enums.Language;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userEmail;
    @Enumerated(EnumType.STRING)
    private Language defaultSource = Language.EN;
    @Enumerated(EnumType.STRING)
    private Language defaultTarget = Language.AZ;
    private boolean darkMode = false;
}
