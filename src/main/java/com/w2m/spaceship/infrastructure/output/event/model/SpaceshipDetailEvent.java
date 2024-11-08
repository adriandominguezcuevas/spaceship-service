package com.w2m.spaceship.infrastructure.output.event.model;

import com.w2m.spaceship.domain.enums.ActionEventType;
import com.w2m.spaceship.domain.enums.SpaceShipStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceshipDetailEvent {

    private ActionEventType action;

    private UUID id;

    private String name;

    private String model;

    private String planetOrigin;

    private String planetDestination;

    private Float fuelTank;

    private Float fuelTankLevel;

    private SpaceShipStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
