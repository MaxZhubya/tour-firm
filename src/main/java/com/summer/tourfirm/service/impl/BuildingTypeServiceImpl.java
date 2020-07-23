package com.summer.tourfirm.service.impl;

import com.summer.tourfirm.dto.BuildingTypeDTO;
import com.summer.tourfirm.dto.edit.BuildingTypeEditDTO;
import com.summer.tourfirm.entity.types.BuildingType;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.exception.DataValidationException;
import com.summer.tourfirm.repository.BuildingTypeRepository;
import com.summer.tourfirm.service.IBuildingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuildingTypeServiceImpl implements IBuildingTypeService {

    @Autowired
    private BuildingTypeRepository repository;

    @Override
    @Transactional(readOnly = true)
    public BuildingTypeDTO get(Long id) {
        return BuildingTypeDTO.makeDTO(getEntity(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BuildingTypeDTO> getAll() {
        return repository.findByOrderByIdAsc().stream()
                .map(BuildingTypeDTO::makeDTO).collect(Collectors.toList());
    }

    @Override
    public BuildingTypeDTO create(BuildingTypeEditDTO typeEditDTO) {
        BuildingType type = new BuildingType()
                .setType(typeEditDTO.getType());

        type = repository.save(type);

        setInputData(type, typeEditDTO);

        return BuildingTypeDTO.makeDTO(repository.save(type));
    }

    @Override
    public BuildingTypeDTO update(BuildingTypeEditDTO typeEditDTO) {
        if (Objects.isNull(typeEditDTO.getId()))
            throw new DataValidationException("ID can't be null!");

        BuildingType type = getEntity(typeEditDTO.getId());

        clearRelatedData(type);

        setInputData(type, typeEditDTO);

        return BuildingTypeDTO.makeDTO(repository.save(type));
    }

    @Override
    public void delete(Long id) {
        BuildingType type = getEntity(id);

        clearRelatedData(type);

        repository.delete(type);
    }

    @Override
    public BuildingType save(BuildingType type) {
        return repository.save(type);
    }

    @Override
    public List<BuildingType> save(List<BuildingType> types) {
        return repository.saveAll(types);
    }

    @Override
    public BuildingType getEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("BuildingType with id: "
                + id.toString() + " is not existed"));
    }

    @Override
    public List<BuildingType> getEntitiesByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }


    public void setInputData(final BuildingType type, BuildingTypeEditDTO typeEditDTO) {
        type.setType(typeEditDTO.getType());
    }

    public void clearRelatedData(BuildingType type) {
        // No related data
    }
}
