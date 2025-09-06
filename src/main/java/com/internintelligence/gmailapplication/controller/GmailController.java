package com.internintelligence.gmailapplication.controller;

import com.internintelligence.gmailapplication.model.dto.GmailDto;
import com.internintelligence.gmailapplication.model.entity.Gmail;
import com.internintelligence.gmailapplication.model.response.ApiResponse;
import com.internintelligence.gmailapplication.service.GmailService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/emails")
public class GmailController {

    private final GmailService service;

    public GmailController(GmailService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<Page<GmailDto.EmailSummary>> inbox(
            @RequestParam(defaultValue = "me@example.com") String owner,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        var pageable = PageRequest.of(page, size);
        Page<Gmail> mails = service.listInbox(owner, pageable);
        var mapped = new PageImpl<>(mails.getContent().stream().map(m -> new GmailDto.EmailSummary(
                m.getId(),
                m.getFromAddress(),
                m.getSubject(),
                m.getBody() == null ? "" : m.getBody().substring(0, Math.min(80, m.getBody().length())),
                m.isStarred(),
                m.getReceivedAt() == null ? null : m.getReceivedAt().toString()
        )).collect(Collectors.toList()), pageable, mails.getTotalElements());
        return ApiResponse.ok(mapped);

}



@GetMapping("/starred")
public ApiResponse<Page<GmailDto.EmailSummary>> starred(
        @RequestParam(defaultValue = "me@example.com") String owner,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
){
    var pageable = PageRequest.of(page, size);
    Page<Gmail> mails = service.listStarred(owner, pageable);
    var mapped = mails.map(m -> new GmailDto.EmailSummary(
            m.getId(), m.getFromAddress(), m.getSubject(),
            m.getBody() == null ? "" : m.getBody().substring(0, Math.min(80, m.getBody().length())),
            m.isStarred(),
            m.getReceivedAt() == null ? null : m.getReceivedAt().toString()
    ));
    return ApiResponse.ok(mapped);
}


@GetMapping("/{id}")
public ApiResponse<Gmail> detail(@PathVariable Long id){
    return ApiResponse.ok(service.getMail(id));
}


// ComposePage
@PostMapping
public ApiResponse<Gmail> compose(@Valid @RequestBody GmailDto.ComposeRequest req){
    return ApiResponse.ok("Sent", service.compose(req));
}


// Star/Unstar
@PostMapping("/{id}/star")
public ApiResponse<Gmail> toggleStar(@PathVariable Long id){
    return ApiResponse.ok(service.toggleStar(id));
}

}
