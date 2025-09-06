package com.internintelligence.gmailapplication.repository;

import com.internintelligence.gmailapplication.model.entity.AppSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppSettingsRepository extends JpaRepository<AppSettings, Integer> {
    Optional<AppSettings> findByUserEmail(String userEmail);
}
