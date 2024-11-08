package com.w2m.spaceship.application.validator;

import com.w2m.spaceship.domain.model.spaceship.CreateSpaceshipCommand;
import com.w2m.spaceship.domain.model.spaceship.UpdateSpaceshipCommand;
import com.w2m.spaceship.exception.FuelTankLevelExceedsCapacityException;
import org.springframework.stereotype.Component;

@Component
public class FuelTankValidator {

    public final static String MESSAGE = "Fuel tank level must be less than fuel tank";

    public void validateFuelTankLevel(CreateSpaceshipCommand command) {
        if (command.getFuelTankLevel() > command.getFuelTank()) {
            throw new FuelTankLevelExceedsCapacityException(MESSAGE);
        }
    }

    public void validateFuelTankLevel(UpdateSpaceshipCommand command) {
        if (command.getFuelTankLevel() > command.getFuelTank()) {
            throw new FuelTankLevelExceedsCapacityException(MESSAGE);
        }
    }
}
