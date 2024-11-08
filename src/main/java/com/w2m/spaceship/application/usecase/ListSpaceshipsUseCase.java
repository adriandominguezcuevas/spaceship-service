package com.w2m.spaceship.application.usecase;

import com.w2m.spaceship.domain.model.spaceship.PagedResult;
import com.w2m.spaceship.domain.model.spaceship.Pagination;
import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;

public interface ListSpaceshipsUseCase {

    PagedResult<SpaceshipDetail> execute(Pagination pagination);

}
