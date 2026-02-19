package com.scraptreasure.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateScrapRequestDto {

    private String address;
    private LocalDateTime preferredTime;
}
