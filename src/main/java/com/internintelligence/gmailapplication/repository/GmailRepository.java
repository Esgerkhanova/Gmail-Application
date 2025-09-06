package com.internintelligence.gmailapplication.repository;

import com.internintelligence.gmailapplication.model.entity.Gmail;

import com.internintelligence.gmailapplication.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GmailRepository extends JpaRepository<Gmail, Long> {
    Page<Gmail> findByOwnerAndFolderOrderByReceivedAtDesc(User owner, Gmail.Folder folder, Pageable pageable);
    Page<Gmail> findByOwnerAndStarredTrueOrderByReceivedAtDesc(User owner, Pageable pageable);

}
