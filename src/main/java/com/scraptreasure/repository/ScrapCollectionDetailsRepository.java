package com.scraptreasure.repository;

import com.scraptreasure.entity.ScrapCollectionDetails;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapCollectionDetailsRepository
        extends JpaRepository<ScrapCollectionDetails, Long> {

           
            Optional<ScrapCollectionDetails> findByScrapRequestId(Long requestId);

}


