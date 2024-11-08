package com.w2m.spaceship.domain.ports.spaceship;

import com.w2m.spaceship.domain.model.spaceship.*;

import java.util.Optional;
import java.util.UUID;

public interface SpaceshipRepositoryPort {

    SpaceshipDetail save(CreateSpaceshipCommand command);

    Optional<SpaceshipDetail> findById(UUID id);

    SpaceshipDetail update(UpdateSpaceshipCommand command);

    PagedResult<SpaceshipDetail> findAll(Pagination pagination);

    void delete(UUID id);

    PagedResult<SpaceshipDetail> findByName(SearchSpaceshipByNameCommand command);

}
