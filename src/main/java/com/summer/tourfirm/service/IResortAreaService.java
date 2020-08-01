package com.summer.tourfirm.service;

import com.summer.tourfirm.dto.ResortAreaDTO;
import com.summer.tourfirm.dto.edit.ResortAreaEditDTO;
import com.summer.tourfirm.entity.ResortArea;

import java.util.List;

public interface IResortAreaService {

    ResortAreaDTO get(Long id);      // Read
    List<ResortAreaDTO> getAll();    // Read
    ResortAreaDTO create(ResortAreaEditDTO resortAreaEditDTO);   // Create
    ResortAreaDTO update(ResortAreaEditDTO resortAreaEditDTO);   // Update
    void delete(Long id);   // Delete
    ResortArea save(ResortArea resortArea);
    List<ResortArea> save(List<ResortArea> areas);
    ResortArea getEntity(Long id);
    List<ResortArea> getEntitiesByIds(List<Long> ids);


}
