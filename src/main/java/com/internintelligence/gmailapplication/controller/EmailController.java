package com.internintelligence.gmailapplication.controller;

import com.internintelligence.gmailapplication.model.dto.EmailDto;
import com.internintelligence.gmailapplication.model.dto.request.EmailRequest;
import com.internintelligence.gmailapplication.model.dto.response.EmailResponse;
import com.internintelligence.gmailapplication.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }


    @GetMapping("/inbox")
    public ResponseEntity<List<EmailDto>> getInbox() {
        return ResponseEntity.ok(service.getInbox());
    }


    @GetMapping("/starred")
    public ResponseEntity<List<EmailDto>> getStarred() {
        return ResponseEntity.ok(service.getStarred());
    }


    @GetMapping("/sent")
    public ResponseEntity<List<EmailDto>> getSent() {
        return ResponseEntity.ok(service.getSent());
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmailResponse> getEmail(@PathVariable Long id) {
        return service.markRead(id)
                .map(e -> ResponseEntity.ok(service.mapToResponse(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/compose")
    public ResponseEntity<EmailResponse> compose(@RequestBody EmailRequest request) {
        var sent = service.compose(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.mapToResponse(sent));
    }

  @PutMapping("/{id}/star")
    public ResponseEntity<EmailResponse> star(@PathVariable Long id) {
        return service.star(id)
                .map(service::mapToResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}/unstar")
    public ResponseEntity<EmailResponse> unstar(@PathVariable Long id) {
        return service.unstar(id)
                .map(service::mapToResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
