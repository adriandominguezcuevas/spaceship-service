package com.w2m.spaceship.infrastructure.output.database.adapter.spaceship;

import com.w2m.spaceship.domain.model.spaceship.*;
import com.w2m.spaceship.domain.ports.spaceship.SpaceshipRepositoryPort;
import com.w2m.spaceship.infrastructure.output.database.entity.spaceship.SpaceShipEntity;
import com.w2m.spaceship.infrastructure.output.database.mapper.spaceship.SpaceshipRepositoryAdapterMapper;
import com.w2m.spaceship.infrastructure.output.database.repository.spaceship.SpaceshipJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SpaceshipRepositoryAdapter implements SpaceshipRepositoryPort {

    private final SpaceshipJpaRepository spaceshipJpaRepository;
    private final SpaceshipRepositoryAdapterMapper spaceshipRepositoryAdapterMapper;

    public SpaceshipRepositoryAdapter(SpaceshipJpaRepository spaceshipJpaRepository, SpaceshipRepositoryAdapterMapper spaceshipRepositoryAdapterMapper) {
        this.spaceshipJpaRepository = spaceshipJpaRepository;
        this.spaceshipRepositoryAdapterMapper = spaceshipRepositoryAdapterMapper;
    }

    @Override
    public SpaceshipDetail save(CreateSpaceshipCommand command) {
        return spaceshipRepositoryAdapterMapper.asSpaceshipDetail(
                spaceshipJpaRepository.save(spaceshipRepositoryAdapterMapper.asSpaceShipEntity(command))
        );
    }

    @Override
    public Optional<SpaceshipDetail> findById(UUID id) {
        return spaceshipJpaRepository.findById(id)
                .map(spaceshipRepositoryAdapterMapper::asSpaceshipDetail);
    }

    @Override
    public SpaceshipDetail update(UpdateSpaceshipCommand command) {
        SpaceShipEntity existingEntity = spaceshipJpaRepository.findById(command.getId())
                .orElseThrow(() -> new EntityNotFoundException("Spaceship not found with id " + command.getId()));

        spaceshipRepositoryAdapterMapper.asSpaceShipEntity(command, existingEntity);

        return spaceshipRepositoryAdapterMapper.asSpaceshipDetail(spaceshipJpaRepository.save(existingEntity));
    }

    @Override
    public PagedResult<SpaceshipDetail> findAll(Pagination pagination) {
        Sort sort = pagination.getSortDirection().equalsIgnoreCase("asc") ?
                Sort.by(pagination.getSortBy()).ascending() :
                Sort.by(pagination.getSortBy()).descending();
        PageRequest pageRequest = PageRequest.of(pagination.getPage(), pagination.getSize(), sort);
        Page<SpaceShipEntity> pageResult = spaceshipJpaRepository.findAll(pageRequest);

        List<SpaceshipDetail> spaceships = pageResult.getContent()
                .stream()
                .map(spaceshipRepositoryAdapterMapper::asSpaceshipDetail)
                .collect(Collectors.toList());

        return new PagedResult<>(
                spaceships,
                pagination.getPage(),
                pagination.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages()
        );
    }

    @Override
    public void delete(UUID id) {
        spaceshipJpaRepository.deleteById(id);
    }

    @Override
    public PagedResult<SpaceshipDetail> findByName(SearchSpaceshipByNameCommand command) {
        PageRequest pageRequest = PageRequest.of(command.getPagination().getPage(), command.getPagination().getSize());
        Page<SpaceShipEntity> pageResult = spaceshipJpaRepository.findByNameContainingIgnoreCase(command.getName(), pageRequest);

        List<SpaceshipDetail> spaceships = pageResult.getContent()
                .stream()
                .map(spaceshipRepositoryAdapterMapper::asSpaceshipDetail)
                .collect(Collectors.toList());

        return new PagedResult<>(
                spaceships,
                command.getPagination().getPage(),
                command.getPagination().getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages()
        );
    }
}
