package com.scraptreasure.dto;

import com.scraptreasure.enums.RequestStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CollectorAcceptResponseDto {

    private Long requestId;
    private RequestStatus status;
    private String collectorEmail;
}

