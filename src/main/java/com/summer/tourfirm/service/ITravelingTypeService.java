package com.summer.tourfirm.service;

import com.summer.tourfirm.dto.TravelingTypeDTO;
import com.summer.tourfirm.dto.edit.TravelingTypeEditDTO;
import com.summer.tourfirm.entity.types.TravelingType;

import java.util.List;

public interface ITravelingTypeService {

    TravelingTypeDTO get(Long id);      // Read
    List<TravelingTypeDTO> getAll();    // Read
    TravelingTypeDTO create(TravelingTypeEditDTO typeEditDTO);   // Create
    TravelingTypeDTO update(TravelingTypeEditDTO typeEditDTO);   // Update
    void delete(Long id);   // Delete
    TravelingType save(TravelingType type);
    List<TravelingType> save(List<TravelingType> types);
    TravelingType getEntity(Long id);
    List<TravelingType> getEntitiesByIds(List<Long> ids);
}
