package com.summer.tourfirm.service.impl;

import com.summer.tourfirm.dto.ResortCityDTO;
import com.summer.tourfirm.dto.edit.ResortCityEditDTO;
import com.summer.tourfirm.entity.Country;
import com.summer.tourfirm.entity.ResortArea;
import com.summer.tourfirm.entity.ResortCity;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.exception.DataValidationException;
import com.summer.tourfirm.repository.ResortCityRepository;
import com.summer.tourfirm.service.ICountryService;
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
public class ResortCityServiceImpl implements IResortCityService {

    @Autowired
    private ICountryService countryService;

    @Autowired
    private IResortAreaService areaService;

    @Autowired
    private ResortCityRepository repository;



    @Override
    @Transactional(readOnly = true)
    public ResortCityDTO get(Long id) {
        return ResortCityDTO.makeDTO(getEntity(id));
    }


    @Override
    @Transactional(readOnly = true)
    public List<ResortCityDTO> getAll() {
        return repository.findByOrderByIdAsc().stream()
                .map(ResortCityDTO::makeDTO).collect(Collectors.toList());
    }


    @Override
    public ResortCityDTO create(ResortCityEditDTO resortCityEditDTO) {
        ResortCity city = new ResortCity()
                .setAbleForEntering(resortCityEditDTO.getAbleForEntering())
                .setEntranceTypes(resortCityEditDTO.getEntranceTypes())
                .setTravelingTypes(resortCityEditDTO.getTravelingTypes());

        city = repository.save(city);

        setInputData(city, resortCityEditDTO);

        return ResortCityDTO.makeDTO(repository.save(city));
    }


    @Override
    public ResortCityDTO update(ResortCityEditDTO resortCityEditDTO) {
        if (Objects.isNull(resortCityEditDTO.getId()))
            throw new DataValidationException("ID can't be null!");

        ResortCity city = getEntity(resortCityEditDTO.getId());

        clearRelatedData(city);

        setInputData(city, resortCityEditDTO);

        return ResortCityDTO.makeDTO(repository.save(city));
    }


    @Override
    public void delete(Long id) {
        ResortCity city = getEntity(id);

        clearRelatedData(city);

        repository.delete(city);
    }


    @Override
    public ResortCity save(ResortCity resortCity) {
        return repository.save(resortCity);
    }


    @Override
    public ResortCity getEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("ResortCity with id: "
                + id.toString() + " is not existed"));
    }


    @Override
    public List<ResortCity> getEntitiesByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }


    private void setInputData(final ResortCity city, ResortCityEditDTO cityEditDTO) {
        city.setAbleForEntering(cityEditDTO.getAbleForEntering());

        if (Objects.nonNull(cityEditDTO.getCountryId())) {
            city.setCountry(countryService.getEntity(cityEditDTO.getCountryId()));
        }

        if (!cityEditDTO.getAreaIds().isEmpty()) {
            List<ResortArea> areas = areaService.getEntitiesByIds(cityEditDTO.getAreaIds());
            if (areas.size() != cityEditDTO.getAreaIds().size())
                throw new DataValidationException("ResortArea ids are wrong!");
            areas.forEach(area -> area.setCity(city));
            city.setAreas(areas);
        }

        if (!cityEditDTO.getEntranceTypes().isEmpty()) {
            if (city.getEntranceTypes().size() != cityEditDTO.getEntranceTypes().size())
                throw new DataValidationException("Wrong types!");
            city.setEntranceTypes(cityEditDTO.getEntranceTypes());
        }

        if (!cityEditDTO.getTravelingTypes().isEmpty()) {
            if (city.getTravelingTypes().size() != cityEditDTO.getTravelingTypes().size())
                throw new DataValidationException("Wrong types!");
            city.setTravelingTypes(cityEditDTO.getTravelingTypes());
        }
    }


    private void clearRelatedData(ResortCity city) {
        Country country = city.getCountry();
        if (Objects.nonNull(country)) {
            country.getCities().remove(city);
            countryService.save(country);
        }
        city.setCountry(null);

        List<ResortArea> areas = city.getAreas();
        areas.forEach(area -> area.setCity(null));
        areaService.save(areas);
        city.getAreas().clear();
    }









}
