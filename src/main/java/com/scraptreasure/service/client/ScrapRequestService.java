package com.scraptreasure.service.client;

import com.scraptreasure.dto.CreateScrapRequestDto;
import com.scraptreasure.entity.ScrapRequest;
import com.scraptreasure.entity.User;
import com.scraptreasure.enums.RequestStatus;
import com.scraptreasure.repository.ScrapRequestRepository;
import com.scraptreasure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScrapRequestService {

    private final ScrapRequestRepository scrapRequestRepository;
    private final UserRepository userRepository;

    public ScrapRequest createRequest(
            CreateScrapRequestDto dto,
            String clientEmail
    )
     {

        User client = userRepository.findByEmail(clientEmail)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        ScrapRequest request = ScrapRequest.builder()
                .client(client)
                .address(dto.getAddress())
                .preferredTime(dto.getPreferredTime())
                .status(RequestStatus.REQUESTED)
                .createdAt(LocalDateTime.now())
                .build();

        return scrapRequestRepository.save(request);
    }

  

    
}

