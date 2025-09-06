package com.internintelligence.gmailapplication.service;

import com.internintelligence.gmailapplication.model.dto.GmailDto;
import com.internintelligence.gmailapplication.model.entity.Gmail;
import com.internintelligence.gmailapplication.model.entity.User;
import com.internintelligence.gmailapplication.repository.GmailRepository;
import com.internintelligence.gmailapplication.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class GmailService {
    private final GmailRepository emailRepository;
    private final UserRepository userRepository;


public User getOrCreateUser(String email){
    return userRepository.findByEmail(email)
            .orElseGet(() -> userRepository.save(User.builder().email(email).displayName(email).build()));
}


public Page<Gmail> listInbox(String ownerEmail, Pageable pageable){
    User owner = getOrCreateUser(ownerEmail);
    return emailRepository.findByOwnerAndFolderOrderByReceivedAtDesc(owner, Gmail.Folder.INBOX, pageable);
}


public Page<Gmail> listStarred(String ownerEmail, Pageable pageable){
    User owner = getOrCreateUser(ownerEmail);
    return emailRepository.findByOwnerAndStarredTrueOrderByReceivedAtDesc(owner, pageable);
}


@Transactional
public Gmail compose(GmailDto.ComposeRequest req){
    User owner = getOrCreateUser(req.from());
    Gmail email = Gmail.builder()
            .owner(owner)
            .fromAddress(req.from())
            .toAddress(req.to())
            .subject(req.subject())
            .body(req.body())
            .folder(Gmail.Folder.SENT)
            .receivedAt(OffsetDateTime.now())
            .build();
    return emailRepository.save(email);
}


public Gmail getMail(Long id){
    return emailRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Gmail not found"));
}


@Transactional
public Gmail toggleStar(Long id){
    Gmail e = getMail(id);
    e.setStarred(!e.isStarred());
    return emailRepository.save(e);
}
}