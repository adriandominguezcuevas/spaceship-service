package com.w2m.spaceship.application.usecase;

import com.w2m.spaceship.domain.model.spaceship.CreateSpaceshipCommand;
import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;

public interface CreateSpaceshipUseCase {

    SpaceshipDetail execute(CreateSpaceshipCommand command);

}
