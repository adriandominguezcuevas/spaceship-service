package com.w2m.spaceship.infrastructure.output.database.adapter.user;

import com.w2m.spaceship.domain.model.user.UserDetail;
import com.w2m.spaceship.domain.ports.user.UserRepositoryPort;
import com.w2m.spaceship.infrastructure.output.database.mapper.user.UserRepositoryAdapterMapper;
import com.w2m.spaceship.infrastructure.output.database.repository.user.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final UserRepositoryAdapterMapper userRepositoryAdapterMapper;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository, UserRepositoryAdapterMapper userRepositoryAdapterMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userRepositoryAdapterMapper = userRepositoryAdapterMapper;
    }

    @Override
    public Optional<UserDetail> findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(userRepositoryAdapterMapper::asUserDetail);
    }
}
