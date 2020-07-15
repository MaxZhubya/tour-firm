package com.summer.tourfirm.service;

import com.summer.tourfirm.dto.LiveBuildingDTO;
import com.summer.tourfirm.dto.edit.LiveBuildingEditDTO;
import com.summer.tourfirm.entity.LiveBuilding;

import java.util.List;

public interface ILiveBuildingService {

    LiveBuildingDTO get(Long id);      // Read
    List<LiveBuildingDTO> getAll();    // Read
    LiveBuildingDTO create(LiveBuildingEditDTO liveBuildingEditDTO);   // Create
    LiveBuildingDTO update(LiveBuildingEditDTO liveBuildingEditDTO);   // Update
    void delete(Long id);   // Delete
    LiveBuilding save(LiveBuilding liveBuilding);
    LiveBuilding getEntity(Long id);
    List<LiveBuilding> getEntitiesByIds(List<Long> ids);
}
