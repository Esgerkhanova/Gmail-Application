package com.internintelligence.gmailapplication.repository;

import com.internintelligence.gmailapplication.model.entity.Translation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends JpaRepository<Translation, Integer> {
    Page<Translation> findByUserEmailOrderByCreatedAtDesc(String userEmail, Pageable pageable);
    Page<Translation> findByUserEmailAndSavedTrueOrderByCreatedAtDesc(String userEmail, Pageable pageable);

}
