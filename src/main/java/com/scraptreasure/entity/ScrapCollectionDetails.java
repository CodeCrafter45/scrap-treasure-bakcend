package com.scraptreasure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "scrap_collection_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrapCollectionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "request_id", nullable = false)
    private ScrapRequest scrapRequest;

    private Double weightKg;
    private Double price;
}
