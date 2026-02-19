package com.scraptreasure.service.admin;

import com.scraptreasure.dto.AdminCollectorDto;
import com.scraptreasure.entity.User;
import com.scraptreasure.enums.Role;
import com.scraptreasure.exception.ResourceNotFoundException;
import com.scraptreasure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminCollectorService {

    private final UserRepository userRepository;

    public List<AdminCollectorDto> getAllCollectors() {

        return userRepository.findByRole(Role.COLLECTOR)
                .stream()
                .map(user -> AdminCollectorDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .enabled(user.isEnabled())
                        .build())
                .collect(Collectors.toList());
    }

    public void verifyCollector(Long collectorId) {

        User collector = userRepository.findById(collectorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Collector not found"));

        collector.setEnabled(true);
        userRepository.save(collector);
    }
}
