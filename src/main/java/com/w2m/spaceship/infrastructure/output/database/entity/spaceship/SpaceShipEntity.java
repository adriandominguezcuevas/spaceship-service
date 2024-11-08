package com.w2m.spaceship.infrastructure.output.database.entity.spaceship;

import com.w2m.spaceship.domain.enums.SpaceShipStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "spaceship")
public class SpaceShipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "planet_origin")
    private String planetOrigin;

    @Column(name = "planet_destination")
    private String planetDestination;

    @Column(name = "fuel_tank")
    private Float fuelTank;

    @Column(name = "fuel_tank_level")
    private Float fuelTankLevel;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private SpaceShipStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}