package com.w2m.spaceship.infrastructure.output.database.repository.spaceship;

import com.w2m.spaceship.infrastructure.output.database.entity.spaceship.SpaceShipEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpaceshipJpaRepository extends JpaRepository<SpaceShipEntity, UUID> {

    Page<SpaceShipEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
