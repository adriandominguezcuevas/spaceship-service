package com.w2m.spaceship.domain.model.spaceship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchSpaceshipByNameCommand {

    private String name;

    private Pagination pagination;

}
