package com.w2m.spaceship.infrastructure.input.rest.dto.spaceship;

import com.w2m.spaceship.domain.enums.SpaceShipStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSpaceshipRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Model is required")
    private String model;

    private String planetOrigin;

    private String planetDestination;

    @DecimalMin(value = "0", message = "Fuel tank must be greater than 0")
    private Float fuelTank;

    @DecimalMin(value = "0", message = "Fuel tank level must be greater than 0")
    private Float fuelTankLevel;

    private SpaceShipStatus status;

}
