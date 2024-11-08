package com.w2m.spaceship.application.usecase.impl;

import com.w2m.spaceship.application.validator.FuelTankValidator;
import com.w2m.spaceship.domain.model.spaceship.CreateSpaceshipCommand;
import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;
import com.w2m.spaceship.domain.ports.spaceship.SpaceshipRepositoryPort;
import com.w2m.spaceship.exception.FuelTankLevelExceedsCapacityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateSpaceshipUseCaseImplTest {

    @Mock
    private SpaceshipRepositoryPort spaceshipRepositoryPort;

    @Mock
    private FuelTankValidator fuelTankValidator;

    @InjectMocks
    private CreateSpaceshipUseCaseImpl createSpaceshipUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create spaceship successfully")
    void shouldCreateSpaceshipSuccessfully() {
        CreateSpaceshipCommand command = new CreateSpaceshipCommand(/* parameters */);
        SpaceshipDetail expectedSpaceshipDetail = new SpaceshipDetail(/* parameters */);

        when(spaceshipRepositoryPort.save(command)).thenReturn(expectedSpaceshipDetail);

        SpaceshipDetail result = createSpaceshipUseCaseImpl.execute(command);

        assertEquals(expectedSpaceshipDetail, result);
        verify(fuelTankValidator).validateFuelTankLevel(command);
        verify(spaceshipRepositoryPort).save(command);
    }

    @Test
    @DisplayName("Should throw exception when fuel tank validation fails")
    void shouldThrowExceptionWhenFuelTankValidationFails() {
        CreateSpaceshipCommand command = new CreateSpaceshipCommand(/* parameters */);

        doThrow(new FuelTankLevelExceedsCapacityException("Fuel tank level must be less than fuel tank")).when(fuelTankValidator).validateFuelTankLevel(command);

        assertThrows(FuelTankLevelExceedsCapacityException.class, () -> createSpaceshipUseCaseImpl.execute(command));

        verify(fuelTankValidator).validateFuelTankLevel(command);
        verify(spaceshipRepositoryPort, never()).save(command);
    }
}
