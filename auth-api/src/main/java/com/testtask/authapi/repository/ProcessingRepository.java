package com.testtask.authapi.repository;

import com.testtask.authapi.models.ProcessingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessingRepository extends JpaRepository<ProcessingLog, UUID> {
}
