package com.w2m.spaceship.application.usecase;

import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;

import java.util.Optional;
import java.util.UUID;

public interface GetSpaceshipByIdUseCase {

    Optional<SpaceshipDetail> execute(UUID id);

}
