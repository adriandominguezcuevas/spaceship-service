package com.w2m.spaceship.domain.model.spaceship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {

    private Integer page;

    private Integer size;

    private String sortBy;

    private String sortDirection;

}
