package com.w2m.spaceship.application.usecase.impl;

import com.w2m.spaceship.application.usecase.ListSpaceshipsUseCase;
import com.w2m.spaceship.domain.model.spaceship.PagedResult;
import com.w2m.spaceship.domain.model.spaceship.Pagination;
import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;
import com.w2m.spaceship.domain.ports.spaceship.SpaceshipRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ListSpaceshipsUseCaseImpl implements ListSpaceshipsUseCase {

    private final SpaceshipRepositoryPort spaceshipRepositoryPort;

    private static final Logger logger = LoggerFactory.getLogger(ListSpaceshipsUseCaseImpl.class);
    private static final String LOG_HEADER = "[APPLICATION][LIST-SPACESHIPS][USE-CASE]";

    public ListSpaceshipsUseCaseImpl(SpaceshipRepositoryPort spaceshipRepositoryPort) {
        this.spaceshipRepositoryPort = spaceshipRepositoryPort;
    }

    @Override
    public PagedResult<SpaceshipDetail> execute(Pagination pagination) {
        if (pagination != null) {
            logger.info("{} Listing spaceships with pagination: {}", LOG_HEADER, pagination);
            return spaceshipRepositoryPort.findAll(pagination);
        } else {
            logger.warn("{} Pagination is null, skipping listing", LOG_HEADER);
            return new PagedResult<>();
        }
    }
}
