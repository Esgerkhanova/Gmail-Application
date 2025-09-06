package com.internintelligence.gmailapplication.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "email")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Email {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromAddress;
    private String toAddress;
    private String subject;
    @Column(length = 10000)
    private String body;
    private LocalDateTime receivedAt;
    private boolean starred;
    private boolean read;
    private boolean sent;

}
