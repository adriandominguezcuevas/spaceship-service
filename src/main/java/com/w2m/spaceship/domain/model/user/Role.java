package com.w2m.spaceship.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Integer id;

    private String name;

    private Set<UserDetail> users;

}
