package com.summer.tourfirm.service;

import com.summer.tourfirm.dto.BuildingTypeDTO;
import com.summer.tourfirm.dto.edit.BuildingTypeEditDTO;
import com.summer.tourfirm.entity.types.BuildingType;

import java.util.List;

public interface IBuildingTypeService {

    BuildingTypeDTO get(Long id);      // Read
    List<BuildingTypeDTO> getAll();    // Read
    BuildingTypeDTO create(BuildingTypeEditDTO typeEditDTO);   // Create
    BuildingTypeDTO update(BuildingTypeEditDTO typeEditDTO);   // Update
    void delete(Long id);   // Delete
    BuildingType save(BuildingType type);
    List<BuildingType> save(List<BuildingType> types);
    BuildingType getEntity(Long id);
    List<BuildingType> getEntitiesByIds(List<Long> ids);
}
