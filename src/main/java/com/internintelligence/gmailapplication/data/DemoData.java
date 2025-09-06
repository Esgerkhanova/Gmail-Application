package com.internintelligence.gmailapplication.data;

import com.internintelligence.gmailapplication.model.entity.Gmail;
import com.internintelligence.gmailapplication.model.entity.User;
import com.internintelligence.gmailapplication.repository.GmailRepository;
import com.internintelligence.gmailapplication.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@Builder
public class DemoData implements CommandLineRunner {
    private final UserRepository userRepo;
    private final GmailRepository emailRepo;


    @Override
    public void run(String... args) {
        User me = userRepo.findByEmail("me@example.com").orElseGet(() -> userRepo.save(User.builder().email("me@example.com").displayName("Me").build()));
        if(emailRepo.count() == 0){
            IntStream.rangeClosed(1, 25).forEach(i -> emailRepo.save(Gmail.builder()
                    .owner(me)
                    .fromAddress("sender"+i+"@example.com")
                    .toAddress("me@example.com")
                    .subject("Welcome #"+i)
                    .body("This is a demo email body for message #"+i+". Lorem ipsum dolor sit amet...")
                    .folder(Gmail.Folder.INBOX)
                    .receivedAt(OffsetDateTime.now().minusHours(i))
                    .starred(i % 5 == 0)
                    .build()));
        }
    }
}
