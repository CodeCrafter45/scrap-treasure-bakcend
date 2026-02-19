package com.scraptreasure.controller.collector;

import com.scraptreasure.dto.ApiResponse;
import com.scraptreasure.dto.CollectorAcceptResponseDto;
import com.scraptreasure.dto.CollectorRequestDto;
import com.scraptreasure.entity.ScrapRequest;
import com.scraptreasure.service.collector.CollectorRequestService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/collector/requests")
@RequiredArgsConstructor
@PreAuthorize("hasRole('COLLECTOR')")
public class CollectorRequestController {

    private final CollectorRequestService collectorRequestService;

   
  @GetMapping
public ApiResponse<List<CollectorRequestDto>> viewAvailableRequests() {

    List<CollectorRequestDto> requests =
            collectorRequestService.getAvailableRequests();

    return ApiResponse.<List<CollectorRequestDto>>builder()
            .success(true)
            .message("Available requests fetched")
            .data(requests)
            .time(LocalDateTime.now())
            .build();
}

   

   
   @PostMapping("/{id}/accept")
public ResponseEntity<ApiResponse<Object>> acceptRequest(
        @PathVariable Long id,
        Authentication authentication
) {
    String collectorEmail = authentication.getName();

    ScrapRequest request =
            collectorRequestService.acceptRequest(id, collectorEmail);

    CollectorAcceptResponseDto responseDto =
            CollectorAcceptResponseDto.builder()
                    .requestId(request.getId())
                    .status(request.getStatus())
                    .collectorEmail(collectorEmail)
                    .build();

    return ResponseEntity.ok(
            ApiResponse.builder()
                    .success(true)
                    .message("Scrap request accepted successfully")
                    .data(responseDto)
                    .time(LocalDateTime.now())
                    .build()
    );
}

}

//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYXZpQGdtYWlsLmNvbSIsImlhdCI6MTc3MDQ2MTU0MywiZXhwIjoxNzcwNTQ3OTQzfQ.vzpUgx-YJxYri8meXye8y-vlr0VIS25_kLIgrbVijjg