package com.internintelligence.gmailapplication.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gmail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private User owner; // mailbox owner
    @Column(nullable = false)
    private String fromAddress;
    @Column(nullable = false)
    private String toAddress;
    private String subject;
    @Lob
    private String body;
    private boolean starred;
    @Enumerated(EnumType.STRING)
    private Folder folder;
    private OffsetDateTime receivedAt;


    public enum Folder { INBOX, SENT, TRASH, SPAM }
}