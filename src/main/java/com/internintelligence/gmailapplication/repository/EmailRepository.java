package com.internintelligence.gmailapplication.repository;

import com.internintelligence.gmailapplication.model.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findBySentFalseOrderByReceivedAtDesc();
    List<Email> findByStarredTrueOrderByReceivedAtDesc();
    List<Email> findBySentTrueOrderByReceivedAtDesc();

}
