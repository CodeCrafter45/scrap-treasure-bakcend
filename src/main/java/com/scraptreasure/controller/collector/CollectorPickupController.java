package com.scraptreasure.controller.collector;

import com.scraptreasure.dto.ApiResponse;
import com.scraptreasure.dto.CollectScrapDto;
import com.scraptreasure.dto.ScrapCollectedResponseDto;
import com.scraptreasure.entity.ScrapCollectionDetails;
import com.scraptreasure.service.collector.CollectorPickupService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collector/pickup")
@RequiredArgsConstructor
@PreAuthorize("hasRole('COLLECTOR')")
public class CollectorPickupController {

    private final CollectorPickupService pickupService;

@PostMapping("/{requestId}")
public ApiResponse<ScrapCollectedResponseDto> collectScrap(
        @PathVariable Long requestId,
        @RequestBody CollectScrapDto dto
) {
    ScrapCollectionDetails details =
            pickupService.collectScrap(requestId, dto);

    return ApiResponse.<ScrapCollectedResponseDto>builder()
            .success(true)
            .message("Scrap collected successfully")
            .data(ScrapCollectedResponseDto.builder()
                    .requestId(details.getScrapRequest().getId())
                    .weightKg(details.getWeightKg())
                    .price(details.getPrice())
                    .message("Amount calculated automatically")
                    .build())
            .time(LocalDateTime.now())
            .build();
}

}
