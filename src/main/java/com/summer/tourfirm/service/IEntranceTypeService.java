package com.summer.tourfirm.service;

import com.summer.tourfirm.dto.EntranceTypeDTO;
import com.summer.tourfirm.dto.edit.EntranceTypeEditDTO;
import com.summer.tourfirm.entity.types.EntranceType;

import java.util.List;

public interface IEntranceTypeService {

    EntranceTypeDTO get(Long id);      // Read
    List<EntranceTypeDTO> getAll();    // Read
    EntranceTypeDTO create(EntranceTypeEditDTO typeEditDTO);   // Create
    EntranceTypeDTO update(EntranceTypeEditDTO typeEditDTO);   // Update
    void delete(Long id);   // Delete
    EntranceType save(EntranceType type);
    List<EntranceType> save(List<EntranceType> types);
    EntranceType getEntity(Long id);
    List<EntranceType> getEntitiesByIds(List<Long> ids);
}
