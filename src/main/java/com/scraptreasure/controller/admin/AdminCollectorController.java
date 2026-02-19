package com.scraptreasure.controller.admin;

import com.scraptreasure.dto.AdminCollectorDto;
import com.scraptreasure.dto.ApiResponse;
import com.scraptreasure.service.admin.AdminCollectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
    
@RestController
@RequestMapping("/api/admin/collectors")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminCollectorController {

    private final AdminCollectorService adminCollectorService;

    @GetMapping
    public ApiResponse<List<AdminCollectorDto>> getAllCollectors() {

        return ApiResponse.<List<AdminCollectorDto>>builder()
                .success(true)
                .message("Collectors fetched successfully")
                .data(adminCollectorService.getAllCollectors())
                .time(LocalDateTime.now())
                .build();
    }

    @PostMapping("/{collectorId}/verify")
    public ApiResponse<Void> verifyCollector(
            @PathVariable Long collectorId
    ) {
        adminCollectorService.verifyCollector(collectorId);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Collector verified successfully")
                .data(null)
                .time(LocalDateTime.now())
                .build();
    }
}
