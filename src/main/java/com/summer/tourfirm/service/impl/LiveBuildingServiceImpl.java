package com.summer.tourfirm.service.impl;

import com.summer.tourfirm.dto.LiveBuildingDTO;
import com.summer.tourfirm.dto.edit.LiveBuildingEditDTO;
import com.summer.tourfirm.entity.LiveBuilding;
import com.summer.tourfirm.repository.LiveBuildingRepository;
import com.summer.tourfirm.service.IApartmentService;
import com.summer.tourfirm.service.ILiveBuildingService;
import com.summer.tourfirm.service.IResortAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LiveBuildingServiceImpl implements ILiveBuildingService {

    @Autowired
    private IApartmentService apartmentService;

    @Autowired
    private IResortAreaService areaService;

    @Autowired
    private LiveBuildingRepository repository;



    @Override
    public LiveBuildingDTO get(Long id) {
        return null;
    }


    @Override
    public List<LiveBuildingDTO> getAll() {
        return null;
    }


    @Override
    public LiveBuildingDTO create(LiveBuildingEditDTO liveBuildingEditDTO) {
        return null;
    }


    @Override
    public LiveBuildingDTO update(LiveBuildingEditDTO liveBuildingEditDTO) {
        return null;
    }


    @Override
    public void delete(Long id) {

    }


    @Override
    public LiveBuilding save(LiveBuilding liveBuilding) {
        return null;
    }


    @Override
    public LiveBuilding getEntity(Long id) {
        return null;
    }


    @Override
    public List<LiveBuilding> getEntitiesByIds(List<Long> ids) {
        return null;
    }



}
