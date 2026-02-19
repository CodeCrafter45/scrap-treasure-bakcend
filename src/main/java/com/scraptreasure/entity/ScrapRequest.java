package com.scraptreasure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scraptreasure.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "scrap_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@Builder
public class ScrapRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many requests → One client
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @Column(nullable = false)
    private String address;

    private LocalDateTime preferredTime;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime createdAt;

    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "collector_id")
    private User collector;

}

