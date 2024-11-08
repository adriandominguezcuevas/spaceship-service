package com.w2m.spaceship.domain.model.spaceship;

import com.w2m.spaceship.domain.enums.SpaceShipStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSpaceshipCommand {

    private String name;

    private String model;

    private String planetOrigin;

    private String planetDestination;

    private Float fuelTank;

    private Float fuelTankLevel;

    private SpaceShipStatus status;

}
