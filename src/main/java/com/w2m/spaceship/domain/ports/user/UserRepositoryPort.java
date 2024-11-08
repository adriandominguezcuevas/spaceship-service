package com.w2m.spaceship.domain.ports.user;

import com.w2m.spaceship.domain.model.user.UserDetail;

import java.util.Optional;

public interface UserRepositoryPort {

    Optional<UserDetail> findByUsername(String username);

}
