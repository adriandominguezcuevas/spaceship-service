package com.w2m.spaceship.domain.model.spaceship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedResult<T> {

    private List<T> items;

    private Integer page;

    private Integer size;

    private Long totalItems;

    private Integer totalPages;

}
