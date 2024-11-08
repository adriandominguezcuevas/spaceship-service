package com.w2m.spaceship.application.usecase;

import com.w2m.spaceship.domain.model.spaceship.PagedResult;
import com.w2m.spaceship.domain.model.spaceship.SearchSpaceshipByNameCommand;
import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;

public interface SearchSpaceshipsByNameUseCase {

    PagedResult<SpaceshipDetail> execute(SearchSpaceshipByNameCommand command);

}
