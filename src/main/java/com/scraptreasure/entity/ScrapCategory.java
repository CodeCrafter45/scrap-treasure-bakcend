package com.scraptreasure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "scrap_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrapCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private Double pricePerKg;

    @Builder.Default
    private boolean active = true;
}
