package com.summer.tourfirm.service;

import com.summer.tourfirm.dto.ResortCityDTO;
import com.summer.tourfirm.dto.edit.ResortCityEditDTO;
import com.summer.tourfirm.entity.ResortCity;

import java.util.List;

public interface IResortCityService {

    ResortCityDTO get(Long id);      // Read
    List<ResortCityDTO> getAll();    // Read
    ResortCityDTO create(ResortCityEditDTO resortCityEditDTO);   // Create
    ResortCityDTO update(ResortCityEditDTO resortCityEditDTO);   // Update
    void delete(Long id);   // Delete
    ResortCity save(ResortCity resortCity);
    ResortCity getEntity(Long id);
    List<ResortCity> getEntitiesByIds(List<Long> ids);
}
