package com.w2m.spaceship.infrastructure.output.database.repository.user;

import com.w2m.spaceship.infrastructure.output.database.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String username);

}
