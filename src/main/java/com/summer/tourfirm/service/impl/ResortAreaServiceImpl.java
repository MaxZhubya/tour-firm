package com.summer.tourfirm.service.impl;

import com.summer.tourfirm.dto.ResortAreaDTO;
import com.summer.tourfirm.dto.edit.ResortAreaEditDTO;
import com.summer.tourfirm.entity.LiveBuilding;
import com.summer.tourfirm.entity.ResortArea;
import com.summer.tourfirm.entity.ResortCity;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.exception.DataValidationException;
import com.summer.tourfirm.repository.ResortAreaRepository;
import com.summer.tourfirm.service.ILiveBuildingService;
import com.summer.tourfirm.service.IResortAreaService;
import com.summer.tourfirm.service.IResortCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResortAreaServiceImpl implements IResortAreaService {

    @Autowired
    private IResortCityService cityService;

    @Autowired
    private ILiveBuildingService buildingService;

    @Autowired
    private ResortAreaRepository repository;


    @Override
    @Transactional(readOnly = true)
    public ResortAreaDTO get(Long id) {
        return ResortAreaDTO.makeDTO(getEntity(id));
    }


    @Override
    @Transactional(readOnly = true)
    public List<ResortAreaDTO> getAll() {
        return repository.findByOrderByIdAsc().stream()
                .map(ResortAreaDTO::makeDTO).collect(Collectors.toList());
    }


    @Override
    public ResortAreaDTO create(ResortAreaEditDTO resortAreaEditDTO) {
        ResortArea area = new ResortArea()
                .setDefinition(resortAreaEditDTO.getDefinition());

        area = repository.save(area);

        setInputData(area, resortAreaEditDTO);

        return ResortAreaDTO.makeDTO(repository.save(area));
    }


    @Override
    public ResortAreaDTO update(ResortAreaEditDTO resortAreaEditDTO) {
        if (Objects.isNull(resortAreaEditDTO.getId()))
            throw new DataValidationException("ID can't be null!");

        ResortArea area = getEntity(resortAreaEditDTO.getId());

        clearRelatedData(area);

        setInputData(area, resortAreaEditDTO);

        return ResortAreaDTO.makeDTO(repository.save(area));
    }


    @Override
    public void delete(Long id) {
        ResortArea area = getEntity(id);

        clearRelatedData(area);

        repository.delete(area);
    }


    @Override
    public ResortArea save(ResortArea resortArea) {
        return repository.save(resortArea);
    }


    @Override
    public List<ResortArea> save(List<ResortArea> areas) {
        return repository.saveAll(areas);
    }


    @Override
    public ResortArea getEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("ResortArea with id: "
                + id.toString() + " is not existed"));
    }


    @Override
    public List<ResortArea> getEntitiesByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }


    private void setInputData(final ResortArea area, ResortAreaEditDTO areaEditDTO) {
        area.setDefinition(areaEditDTO.getDefinition());

        if (Objects.nonNull(areaEditDTO.getCityId())) {
            area.setCity(cityService.getEntity(areaEditDTO.getCityId()));
        }

        if (!areaEditDTO.getBuildingIds().isEmpty()) {
            List<LiveBuilding> buildings = buildingService.getEntitiesByIds(areaEditDTO.getBuildingIds());
            if (buildings.size() != areaEditDTO.getBuildingIds().size())
                throw new DataValidationException("Building ids are wrong!");
            buildings.forEach(building -> building.setArea(area));
            area.setBuildings(buildings);
        }

    }


    private void clearRelatedData(ResortArea area) {
        ResortCity city = area.getCity();
        if (Objects.nonNull(city)){
                city.getAreas().remove(area);
                cityService.save(city);
        }
        area.setCity(null);

        List<LiveBuilding> buildings = area.getBuildings();
        buildings.forEach(building -> building.setArea(null));
        buildingService.save(buildings);
        area.getBuildings().clear();
    }

}
