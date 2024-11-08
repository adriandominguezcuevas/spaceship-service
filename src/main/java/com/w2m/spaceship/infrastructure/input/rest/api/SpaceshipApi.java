package com.w2m.spaceship.infrastructure.input.rest.api;

import com.w2m.spaceship.domain.model.spaceship.PagedResult;
import com.w2m.spaceship.infrastructure.input.rest.dto.error.ErrorResponse;
import com.w2m.spaceship.infrastructure.input.rest.dto.spaceship.*;
import com.w2m.spaceship.infrastructure.input.rest.dto.user.AuthRequest;
import com.w2m.spaceship.infrastructure.input.rest.dto.user.AuthResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@OpenAPIDefinition
@RequestMapping("/api/v1/spaceships")
public interface SpaceshipApi {

    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request);

    @Operation(summary = "Create a new spaceship", security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "Spaceship created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateSpaceshipResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("")
    ResponseEntity<CreateSpaceshipResponse> createSpaceship(@Valid @RequestBody CreateSpaceshipRequest request);

    @Operation(summary = "List all spaceships", security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "List of spaceships",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PagedResult.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("")
    ResponseEntity<PagedResult<ListSpaceshipResponse>> listSpaceships(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "id") String sortBy
    );

    @Operation(summary = "Get a spaceship by ID", security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spaceship found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetSpaceshipByIdResponse.class))),
            @ApiResponse(
                    responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )

    })
    @GetMapping("/{id}")
    ResponseEntity<GetSpaceshipByIdResponse> getSpaceshipById(@PathVariable UUID id);

    @Operation(summary = "Update an existing spaceship", security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spaceship updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UpdateSpaceshipResponse.class))),
            @ApiResponse(
                    responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "404", description = "Spaceship not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    ResponseEntity<UpdateSpaceshipResponse> updateSpaceship(@PathVariable UUID id, @Valid @RequestBody UpdateSpaceshipRequest request);

    @Operation(summary = "Delete a spaceship", security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Spaceship deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Spaceship not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSpaceship(@PathVariable UUID id);

    @Operation(summary = "Search spaceships by name", security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spaceships found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PagedResult.class))),
            @ApiResponse(
                    responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/search")
    ResponseEntity<PagedResult<ListSpaceshipResponse>> searchSpaceshipsByName(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam String name
    );
}
