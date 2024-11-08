package com.w2m.spaceship.domain.model.spaceship;

import com.w2m.spaceship.domain.enums.SpaceShipStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSpaceshipCommand {

    private UUID id;

    private String name;

    private String model;

    private String planetOrigin;

    private String planetDestination;

    private Float fuelTank;

    private Float fuelTankLevel;

    private SpaceShipStatus status;

}
