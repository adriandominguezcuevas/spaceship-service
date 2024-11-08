package com.w2m.spaceship.infrastructure.input.rest.controller;

import com.w2m.spaceship.application.usecase.*;
import com.w2m.spaceship.domain.model.spaceship.PagedResult;
import com.w2m.spaceship.infrastructure.input.rest.api.SpaceshipApi;
import com.w2m.spaceship.infrastructure.input.rest.dto.spaceship.*;
import com.w2m.spaceship.infrastructure.input.rest.dto.user.AuthRequest;
import com.w2m.spaceship.infrastructure.input.rest.dto.user.AuthResponse;
import com.w2m.spaceship.infrastructure.input.rest.mapper.SpaceshipControllerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SpaceshipController implements SpaceshipApi {

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final CreateSpaceshipUseCase createSpaceshipUseCase;
    private final GetSpaceshipByIdUseCase getSpaceshipByIdUseCase;
    private final UpdateSpaceshipUseCase updateSpaceshipUseCase;
    private final DeleteSpaceshipByIdUseCase deleteSpaceshipByIdUseCase;
    private final ListSpaceshipsUseCase listSpaceshipsUseCase;
    private final SearchSpaceshipsByNameUseCase searchSpaceshipsByNameUseCase;
    private final SpaceshipControllerMapper spaceshipControllerMapper;

    public SpaceshipController(
            AuthenticateUserUseCase authenticateUserUseCase,
            CreateSpaceshipUseCase createSpaceshipUseCase,
            GetSpaceshipByIdUseCase getSpaceshipByIdUseCase,
            UpdateSpaceshipUseCase updateSpaceshipUseCase,
            DeleteSpaceshipByIdUseCase deleteSpaceshipByIdUseCase,
            ListSpaceshipsUseCase listSpaceshipsUseCase,
            SearchSpaceshipsByNameUseCase searchSpaceshipsByNameUseCase,
            SpaceshipControllerMapper spaceshipControllerMapper
    ) {
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.createSpaceshipUseCase = createSpaceshipUseCase;
        this.getSpaceshipByIdUseCase = getSpaceshipByIdUseCase;
        this.updateSpaceshipUseCase = updateSpaceshipUseCase;
        this.deleteSpaceshipByIdUseCase = deleteSpaceshipByIdUseCase;
        this.listSpaceshipsUseCase = listSpaceshipsUseCase;
        this.searchSpaceshipsByNameUseCase = searchSpaceshipsByNameUseCase;
        this.spaceshipControllerMapper = spaceshipControllerMapper;
    }

    @Override
    public ResponseEntity<AuthResponse> login(AuthRequest request) {
        return ResponseEntity.ok(
                spaceshipControllerMapper.asAuthResponse(
                        authenticateUserUseCase.authenticate(request.getUsername(), request.getPassword())
                )
        );
    }

    @Override
    public ResponseEntity<CreateSpaceshipResponse> createSpaceship(CreateSpaceshipRequest request) {
        return ResponseEntity.ok(
                spaceshipControllerMapper.asCreateSpaceshipResponse(
                        createSpaceshipUseCase.execute(spaceshipControllerMapper.asCreateSpaceshipCommand(request))
                )
        );
    }

    @Override
    public ResponseEntity<PagedResult<ListSpaceshipResponse>> listSpaceships(
            Integer page, Integer size, String sortDirection, String sortBy
    ) {
        return ResponseEntity.ok(
                spaceshipControllerMapper.asPagedResult(
                        listSpaceshipsUseCase.execute(
                                spaceshipControllerMapper.asPagination(page, size, sortDirection, sortBy)
                        )
                )
        );
    }

    @Override
    public ResponseEntity<GetSpaceshipByIdResponse> getSpaceshipById(UUID id) {
        return getSpaceshipByIdUseCase.execute(id)
                .map(spaceship -> ResponseEntity.ok(spaceshipControllerMapper.asGetSpaceshipByIdResponse(spaceship)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<UpdateSpaceshipResponse> updateSpaceship(UUID id, UpdateSpaceshipRequest request) {
        return ResponseEntity.ok(
                spaceshipControllerMapper.asUpdateSpaceshipResponse(
                        updateSpaceshipUseCase.execute(spaceshipControllerMapper.asUpdateSpaceshipCommand(id, request))
                )
        );
    }

    @Override
    public ResponseEntity<Void> deleteSpaceship(UUID id) {
        deleteSpaceshipByIdUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PagedResult<ListSpaceshipResponse>> searchSpaceshipsByName(Integer page, Integer size, String name) {
        return ResponseEntity.ok(
                spaceshipControllerMapper.asPagedResult(
                        searchSpaceshipsByNameUseCase.execute(
                                spaceshipControllerMapper.asSearchSpaceshipByNameCommand(page, size, name)
                        )
                )
        );
    }
}
