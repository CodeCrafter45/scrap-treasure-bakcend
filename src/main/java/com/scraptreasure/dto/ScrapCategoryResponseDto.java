package com.scraptreasure.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScrapCategoryResponseDto {

    private Long id;
    private String name;
    private Double pricePerKg;
    private boolean active;
}
