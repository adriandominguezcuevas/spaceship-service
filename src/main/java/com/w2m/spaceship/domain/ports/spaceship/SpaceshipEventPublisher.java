package com.w2m.spaceship.domain.ports.spaceship;

import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;

import java.util.UUID;

public interface SpaceshipEventPublisher {

    public void publishCreateSpaceshipEvent(SpaceshipDetail spaceshipDetail);

    public void publishUpdateSpaceshipEvent(SpaceshipDetail spaceshipDetail);

    public void publishDeleteSpaceshipEvent(UUID id);

}
