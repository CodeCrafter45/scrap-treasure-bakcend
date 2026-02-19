package com.scraptreasure.dto;

import com.scraptreasure.enums.RequestStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ScrapRequestResponseDto {

    private Long requestId;
    private String address;
    private RequestStatus status;
    private LocalDateTime preferredTime;
}
