package com.scraptreasure.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScrapCollectedResponseDto {

    private Long requestId;
    private Double weightKg;
    private Double price;
    private String message;
}
