package com.scraptreasure.controller.admin;

import com.scraptreasure.dto.*;
import com.scraptreasure.service.admin.AdminScrapCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/scrap-categories")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminScrapCategoryController {

    private final AdminScrapCategoryService service;

    @PostMapping
    public ApiResponse<ScrapCategoryResponseDto> create(
            @RequestBody ScrapCategoryRequestDto dto) {

        return ApiResponse.<ScrapCategoryResponseDto>builder()
                .success(true)
                .message("Scrap category created")
                .data(service.createCategory(dto))
                .time(LocalDateTime.now())
                .build();
    }

    @GetMapping
    public ApiResponse<List<ScrapCategoryResponseDto>> getAll() {

        return ApiResponse.<List<ScrapCategoryResponseDto>>builder()
                .success(true)
                .message("Scrap categories fetched")
                .data(service.getAllCategories())
                .time(LocalDateTime.now())
                .build();
    }

    @PostMapping("/{id}/toggle")
    public ApiResponse<Void> toggle(
            @PathVariable Long id,
            @RequestParam boolean active) {

        service.toggleCategory(id, active);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Category status updated")
                .data(null)
                .time(LocalDateTime.now())
                .build();
    }
}

