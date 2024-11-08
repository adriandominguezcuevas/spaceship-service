package com.w2m.spaceship.application.usecase.impl;

import com.w2m.spaceship.application.usecase.GetSpaceshipByIdUseCase;
import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;
import com.w2m.spaceship.domain.ports.spaceship.SpaceshipRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetSpaceshipByIdUseCaseImpl implements GetSpaceshipByIdUseCase {

    private final SpaceshipRepositoryPort spaceshipRepositoryPort;

    private static final Logger logger = LoggerFactory.getLogger(GetSpaceshipByIdUseCaseImpl.class);
    private static final String LOG_HEADER = "[APPLICATION][GET-SPACESHIP-BY-ID][USE-CASE]";

    public GetSpaceshipByIdUseCaseImpl(SpaceshipRepositoryPort spaceshipRepositoryPort) {
        this.spaceshipRepositoryPort = spaceshipRepositoryPort;
    }

    @Override
    @Cacheable(value = "spaceships", key = "#id")
    public Optional<SpaceshipDetail> execute(UUID id) {
        if (id != null) {
            logger.info("{} Looking for spaceship with ID: {}", LOG_HEADER, id);
            return spaceshipRepositoryPort.findById(id);
        } else {
            logger.warn("{} ID is null, skipping search", LOG_HEADER);
            return Optional.empty();
        }
    }

}
