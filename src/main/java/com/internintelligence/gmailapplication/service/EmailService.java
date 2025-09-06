package com.internintelligence.gmailapplication.service;

import com.internintelligence.gmailapplication.model.dto.EmailDto;
import com.internintelligence.gmailapplication.model.dto.request.EmailRequest;
import com.internintelligence.gmailapplication.model.dto.response.EmailResponse;
import com.internintelligence.gmailapplication.model.entity.Email;
import com.internintelligence.gmailapplication.repository.EmailRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailService {

    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }


    public List<EmailDto> getInbox() {
        return emailRepository.findBySentFalseOrderByReceivedAtDesc()
                .stream()
                .map(this::toSummary)
                .collect(Collectors.toList());
    }


    public List<EmailDto> getStarred() {
        return emailRepository.findByStarredTrueOrderByReceivedAtDesc()
                .stream()
                .map(this::toSummary)
                .collect(Collectors.toList());
    }


    public List<EmailDto> getSent() {
        return emailRepository.findBySentTrueOrderByReceivedAtDesc()
                .stream()
                .map(this::toSummary)
                .collect(Collectors.toList());
    }


    public Optional<Email> getById(Long id) {
        return emailRepository.findById(id);
    }


    public Email compose(EmailRequest req) {
        Email e = new Email();
        e.setFromAddress("me@example.com");
        e.setToAddress(req.getTo());
        e.setSubject(req.getSubject());
        e.setBody(req.getBody());
        e.setSent(true);
        e.setReceivedAt(LocalDateTime.now());
        return emailRepository.save(e);
    }


    public Email saveDraft(EmailRequest req) {
        Email e = new Email();
        e.setFromAddress("me@example.com");
        e.setToAddress(req.getTo());
        e.setSubject(req.getSubject());
        e.setBody(req.getBody());
        e.setSent(false);
        e.setReceivedAt(LocalDateTime.now());
        return emailRepository.save(e);
    }


    public Optional<Email> markRead(Long id) {
        return emailRepository.findById(id).map(e -> {
            if (!e.isRead()) {
                e.setRead(true);
                return emailRepository.save(e);
            }
            return e;
        });
    }


    public Optional<Email> star(Long id) {
        return emailRepository.findById(id).map(e -> {
            e.setStarred(true);
            return emailRepository.save(e);
        });
    }


    public Optional<Email> unstar(Long id) {
        return emailRepository.findById(id).map(e -> {
            e.setStarred(false);
            return emailRepository.save(e);
        });
    }


    private EmailDto toSummary(Email e) {
        String snippet = e.getBody() == null ? "" :
                (e.getBody().length() > 100 ? e.getBody().substring(0, 100) + "..." : e.getBody());
        return new EmailDto(
                e.getId(),
                e.getFromAddress(),
                e.getSubject(),
                snippet,
                e.getReceivedAt(),
                e.isStarred(),
                e.isRead()
        );
    }


    public EmailResponse mapToResponse(Email e) {
        String snippet = e.getBody() == null ? "" :
                (e.getBody().length() > 100 ? e.getBody().substring(0, 100) + "..." : e.getBody());
        return new EmailResponse(
                e.getId(),
                e.getFromAddress(),
                e.getToAddress(),
                e.getSubject(),
                snippet,
                e.getReceivedAt(),
                e.isStarred(),
                e.isRead()
        );
    }

}
