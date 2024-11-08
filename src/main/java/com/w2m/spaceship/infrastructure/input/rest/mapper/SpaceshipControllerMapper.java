package com.w2m.spaceship.infrastructure.input.rest.mapper;

import com.w2m.spaceship.domain.model.spaceship.*;
import com.w2m.spaceship.infrastructure.input.rest.dto.spaceship.*;
import com.w2m.spaceship.infrastructure.input.rest.dto.user.AuthResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface SpaceshipControllerMapper {

    CreateSpaceshipCommand asCreateSpaceshipCommand(CreateSpaceshipRequest request);

    CreateSpaceshipResponse asCreateSpaceshipResponse(SpaceshipDetail spaceshipDetail);

    GetSpaceshipByIdResponse asGetSpaceshipByIdResponse(SpaceshipDetail spaceshipDetail);

    UpdateSpaceshipCommand asUpdateSpaceshipCommand(UUID id, UpdateSpaceshipRequest request);

    UpdateSpaceshipResponse asUpdateSpaceshipResponse(SpaceshipDetail spaceshipDetail);

    Pagination asPagination(Integer page, Integer size, String sortDirection, String sortBy);

    PagedResult<ListSpaceshipResponse> asPagedResult(PagedResult<SpaceshipDetail> pagedResult);

    @Mapping(target = "pagination.page", source = "page")
    @Mapping(target = "pagination.size", source = "size")
    SearchSpaceshipByNameCommand asSearchSpaceshipByNameCommand(Integer page, Integer size, String name);

    @Mapping(target = "accessToken", source = "token")
    AuthResponse asAuthResponse(String token);

}
