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
        ResortArea area = new ResortArea();
        setInputData(area, resortAreaEditDTO);
        ResortCity city = cityService.getEntity(resortAreaEditDTO.getCityId());
        area.setCity(city);
        city.getAreas().add(area);
        city = cityService.save(city);
        return ResortAreaDTO.makeDTO(city.getAreaByName(area.getName()));
    }


    @Override
    public ResortAreaDTO update(ResortAreaEditDTO resortAreaEditDTO) {
        if (Objects.isNull(resortAreaEditDTO.getId()))
            throw new DataValidationException("ID can't be null!");

        ResortArea area = getEntity(resortAreaEditDTO.getId());
        setInputData(area, resortAreaEditDTO);
        return ResortAreaDTO.makeDTO(repository.save(area));
    }


    @Override
    public void delete(Long id) {
        ResortArea area = getEntity(id);
        ResortCity city = area.getCity();
        city.getAreas().remove(area);
        cityService.save(city);
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
        area.setName(areaEditDTO.getName());
    }


}
