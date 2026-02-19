package com.scraptreasure.controller.client;

import com.scraptreasure.dto.ApiResponse;
import com.scraptreasure.dto.ClientRequestStatusDto;
import com.scraptreasure.service.client.ClientRequestStatusService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/client/requests")
@RequiredArgsConstructor
@PreAuthorize("hasRole('CLIENT')")
public class ClientRequestStatusController {

    private final ClientRequestStatusService statusService;

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getMyRequests(
            Authentication authentication
    ) {
        String email = authentication.getName();

        List<ClientRequestStatusDto> requests =
                statusService.getMyRequests(email);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Scrap request accepted successfully")
                        .data(requests)
                        .time(LocalDateTime.now())
                        .build()
        );
    }
}
