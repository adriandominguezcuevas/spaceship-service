package com.w2m.spaceship.application.usecase.impl;

import com.w2m.spaceship.application.usecase.UpdateSpaceshipUseCase;
import com.w2m.spaceship.application.validator.FuelTankValidator;
import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;
import com.w2m.spaceship.domain.model.spaceship.UpdateSpaceshipCommand;
import com.w2m.spaceship.domain.ports.spaceship.SpaceshipRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class UpdateSpaceshipUseCaseImpl implements UpdateSpaceshipUseCase {

    private final SpaceshipRepositoryPort spaceshipRepositoryPort;
    private final FuelTankValidator fuelTankValidator;

    private static final Logger logger = LoggerFactory.getLogger(UpdateSpaceshipUseCaseImpl.class);
    private static final String LOG_HEADER = "[APPLICATION][UPDATE-SPACESHIP][USE-CASE]";

    public UpdateSpaceshipUseCaseImpl(
            SpaceshipRepositoryPort spaceshipRepositoryPort,
            FuelTankValidator fuelTankValidator
    ) {
        this.spaceshipRepositoryPort = spaceshipRepositoryPort;
        this.fuelTankValidator = fuelTankValidator;
    }

    @Override
    @CachePut(value = "spaceships", key = "#command.id")
    public SpaceshipDetail execute(UpdateSpaceshipCommand command) {
        logger.info("{} Updating spaceship: {}", LOG_HEADER, command);
        fuelTankValidator.validateFuelTankLevel(command);
        return spaceshipRepositoryPort.update(command);
    }

}
