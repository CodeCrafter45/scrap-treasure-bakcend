package com.scraptreasure.dto;

import com.scraptreasure.enums.RequestStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CollectorRequestDto {

    private Long requestId;
    private String address;
    private RequestStatus status;
    private Double price;
    private Double weightKg;
}
