package com.scraptreasure.repository;

import java.util.Collection;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scraptreasure.dto.AdminCollectorDto;
import  com.scraptreasure.entity.User;
import com.scraptreasure.enums.Role;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    

    boolean existsByEmail(String email);
    Collection<AdminCollectorDto> findByRole(Role collector);
}
//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYXZpQGdtYWlsLmNvbSIsImlhdCI6MTc3MDQ2Mjk1NywiZXhwIjoxNzcwNTQ5MzU3fQ.JEKxZBxPq4S1vw0BmPmc5wPJ_OIYIztN3XpQe6sosnA