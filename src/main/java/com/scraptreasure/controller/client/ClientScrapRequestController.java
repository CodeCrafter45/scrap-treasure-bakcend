package com.scraptreasure.controller.client;

import com.scraptreasure.dto.ApiResponse;
import com.scraptreasure.dto.CreateScrapRequestDto;
import com.scraptreasure.dto.ScrapRequestResponseDto;
import com.scraptreasure.entity.ScrapRequest;
import com.scraptreasure.service.client.ScrapRequestService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/client/requests")
@RequiredArgsConstructor
@PreAuthorize("hasRole('CLIENT')")
public class ClientScrapRequestController {

    private final ScrapRequestService scrapRequestService;

    @PostMapping
    public ResponseEntity<ApiResponse<ScrapRequestResponseDto>> createRequest(
            @RequestBody CreateScrapRequestDto dto,
            Authentication authentication
    ) {
        String email = authentication.getName();

        ScrapRequest request =
                scrapRequestService.createRequest(dto, email);

        ScrapRequestResponseDto responseDto =
                ScrapRequestResponseDto.builder()
                        .requestId(request.getId())
                        .address(request.getAddress())
                        .status(request.getStatus())
                        .preferredTime(request.getPreferredTime())
                        .build();

        return ResponseEntity.ok(
                ApiResponse.<ScrapRequestResponseDto>builder()
                        .success(true)
                        .message("Scrap request created successfully")
                        .data(responseDto)
                        .time(LocalDateTime.now())
                        .build()
        );
    }
}
