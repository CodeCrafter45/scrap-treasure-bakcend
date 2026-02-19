package com.scraptreasure.repository;

import com.scraptreasure.entity.ScrapRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.scraptreasure.enums.RequestStatus;
import java.util.Optional;

import java.util.List;



public interface ScrapRequestRepository extends JpaRepository<ScrapRequest, Long> {

    List<ScrapRequest> findByStatus(RequestStatus status);
     List<ScrapRequest> findByClientId(Long clientId);
    Optional<ScrapRequest> findByIdAndStatus(Long id, RequestStatus status);
}
