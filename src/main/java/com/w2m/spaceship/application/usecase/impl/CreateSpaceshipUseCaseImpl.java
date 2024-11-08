package com.w2m.spaceship.application.usecase.impl;

import com.w2m.spaceship.application.usecase.CreateSpaceshipUseCase;
import com.w2m.spaceship.application.validator.FuelTankValidator;
import com.w2m.spaceship.domain.model.spaceship.CreateSpaceshipCommand;
import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;
import com.w2m.spaceship.domain.ports.spaceship.SpaceshipRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateSpaceshipUseCaseImpl implements CreateSpaceshipUseCase {

    private final SpaceshipRepositoryPort spaceshipRepositoryPort;
    private final FuelTankValidator fuelTankValidator;

    private static final Logger logger = LoggerFactory.getLogger(CreateSpaceshipUseCaseImpl.class);
    private static final String LOG_HEADER = "[APPLICATION][CREATE-SPACESHIP][USE-CASE]";

    public CreateSpaceshipUseCaseImpl(
            SpaceshipRepositoryPort spaceshipRepositoryPort,
            FuelTankValidator fuelTankValidator
    ) {
        this.spaceshipRepositoryPort = spaceshipRepositoryPort;
        this.fuelTankValidator = fuelTankValidator;
    }

    @Override
    public SpaceshipDetail execute(CreateSpaceshipCommand command) {
        logger.info("{} Creating spaceship: {}", LOG_HEADER, command);
        fuelTankValidator.validateFuelTankLevel(command);
        return spaceshipRepositoryPort.save(command);
    }
}
