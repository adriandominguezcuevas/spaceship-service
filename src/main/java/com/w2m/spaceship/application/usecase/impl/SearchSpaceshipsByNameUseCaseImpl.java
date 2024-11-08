package com.w2m.spaceship.application.usecase.impl;

import com.w2m.spaceship.application.usecase.SearchSpaceshipsByNameUseCase;
import com.w2m.spaceship.domain.model.spaceship.PagedResult;
import com.w2m.spaceship.domain.model.spaceship.SearchSpaceshipByNameCommand;
import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;
import com.w2m.spaceship.domain.ports.spaceship.SpaceshipRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SearchSpaceshipsByNameUseCaseImpl implements SearchSpaceshipsByNameUseCase {

    private final SpaceshipRepositoryPort spaceshipRepositoryPort;

    private static final Logger logger = LoggerFactory.getLogger(SearchSpaceshipsByNameUseCaseImpl.class);
    private static final String LOG_HEADER = "[APPLICATION][SEARCH-SPACESHIPS-BY-NAME][USE-CASE]";

    public SearchSpaceshipsByNameUseCaseImpl(SpaceshipRepositoryPort spaceshipRepositoryPort) {
        this.spaceshipRepositoryPort = spaceshipRepositoryPort;
    }

    @Override
    @Cacheable(value = "spaceships", key = "#command.name")
    public PagedResult<SpaceshipDetail> execute(SearchSpaceshipByNameCommand command) {
        if (command != null) {
            logger.info("{} Looking for spaceship with name: {}", LOG_HEADER, command);
            return spaceshipRepositoryPort.findByName(command);
        } else {
            logger.warn("{} Command is null, skipping search", LOG_HEADER);
            return new PagedResult<>();
        }
    }

}
