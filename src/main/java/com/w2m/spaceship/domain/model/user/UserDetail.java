package com.w2m.spaceship.domain.model.user;

import com.w2m.spaceship.infrastructure.output.database.entity.user.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {

    private UUID id;

    private String username;

    private String password;

    private Set<Role> roles;
}
