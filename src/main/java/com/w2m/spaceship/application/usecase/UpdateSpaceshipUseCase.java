package com.w2m.spaceship.application.usecase;

import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;
import com.w2m.spaceship.domain.model.spaceship.UpdateSpaceshipCommand;

public interface UpdateSpaceshipUseCase {

    SpaceshipDetail execute(UpdateSpaceshipCommand command);

}
