package com.scraptreasure.dto;

import com.scraptreasure.enums.RequestStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ClientRequestStatusDto {

    private Long requestId;
    private String address;
    private LocalDateTime preferredTime;
    private RequestStatus status;

    private Double weightKg;
    private Double price;
}
