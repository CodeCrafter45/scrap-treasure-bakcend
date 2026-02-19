package com.scraptreasure.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminCollectorDto {

    private Long id;
    private String name;
    private String email;
    private boolean enabled;
}
