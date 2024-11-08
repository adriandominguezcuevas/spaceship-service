package com.w2m.spaceship.application.usecase.impl;

import com.w2m.spaceship.application.usecase.DeleteSpaceshipByIdUseCase;
import com.w2m.spaceship.domain.ports.spaceship.SpaceshipRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteSpaceshipByIdUseCaseImpl implements DeleteSpaceshipByIdUseCase {

    private final SpaceshipRepositoryPort spaceshipRepositoryPort;

    private static final Logger logger = LoggerFactory.getLogger(DeleteSpaceshipByIdUseCaseImpl.class);
    private static final String LOG_HEADER = "[APPLICATION][DELETE-SPACESHIP-BY-ID][USE-CASE]";

    public DeleteSpaceshipByIdUseCaseImpl(SpaceshipRepositoryPort spaceshipRepositoryPort) {
        this.spaceshipRepositoryPort = spaceshipRepositoryPort;
    }

    @Override
    @CacheEvict(value = "spaceships", key = "#id")
    public void execute(UUID id) {
        if (id != null) {
            logger.info("{} Deleting spaceship with ID: {}", LOG_HEADER, id);
            spaceshipRepositoryPort.delete(id);
        } else {
            logger.warn("{} ID is null, skipping deletion", LOG_HEADER);
        }
    }
}
