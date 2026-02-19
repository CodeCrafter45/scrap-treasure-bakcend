package com.scraptreasure.repository;

import com.scraptreasure.entity.ScrapCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScrapCategoryRepository
        extends JpaRepository<ScrapCategory, Long> {

    Optional<ScrapCategory> findByName(String name);

    List<ScrapCategory> findByActiveTrue();
}
