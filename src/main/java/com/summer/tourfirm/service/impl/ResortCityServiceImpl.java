package com.summer.tourfirm.service.impl;

import com.summer.tourfirm.dto.ResortCityDTO;
import com.summer.tourfirm.dto.edit.ResortCityEditDTO;
import com.summer.tourfirm.entity.Country;
import com.summer.tourfirm.entity.ResortCity;
import com.summer.tourfirm.entity.types.EntranceType;
import com.summer.tourfirm.entity.types.TravelingType;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.exception.DataValidationException;
import com.summer.tourfirm.repository.ResortCityRepository;
import com.summer.tourfirm.service.*;
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
    private IEntranceTypeService entranceTypeService;

    @Autowired
    private ITravelingTypeService travelingTypeService;

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
        ResortCity city = new ResortCity();
        setInputData(city, resortCityEditDTO);
        Country country = countryService.getEntity(resortCityEditDTO.getCountryId());
        city.setCountry(country);
        country.getCities().add(city);
        country = countryService.save(country);
        return ResortCityDTO.makeDTO(country.getCityByName(city.getName()));
    }

    @Override
    public ResortCityDTO update(ResortCityEditDTO resortCityEditDTO) {
        if (Objects.isNull(resortCityEditDTO.getId()))
            throw new DataValidationException("ID can't be null!");

        ResortCity city = getEntity(resortCityEditDTO.getId());
        setInputData(city, resortCityEditDTO);
        return ResortCityDTO.makeDTO(repository.save(city));
    }

    @Override
    public void delete(Long id) {
        ResortCity city = getEntity(id);
        Country country = city.getCountry();
        country.getCities().remove(city);
        countryService.save(country);
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



    /*------ EntranceType ------*/
    @Override
    public ResortCityDTO addEntranceType(Long id, List<Long> entranceTypeIds) {
        List<EntranceType> entranceTypeList = entranceTypeService.getEntitiesByIds(entranceTypeIds);
        if (entranceTypeList.size() != entranceTypeIds.size())
            throw new DataValidationException("EntranceType ids are wrong!");

        ResortCity city = getEntity(id);
        city.getEnterTypes().clear();
        city.getEnterTypes().addAll(entranceTypeList);
        return ResortCityDTO.makeDTO(repository.save(city));
    }

    @Override
    public void deleteEntranceType(Long id, Long typeId) {
        EntranceType entranceType = entranceTypeService.getEntity(typeId);
        ResortCity city = getEntity(id);
        city.getEnterTypes().remove(entranceType);
        repository.save(city);
    }



    /*------ TravelingType ------*/
    @Override
    public ResortCityDTO addTravelingType(Long id, List<Long> travelingTypeIds) {
        List<TravelingType> travelingTypeList = travelingTypeService.getEntitiesByIds(travelingTypeIds);
        if (travelingTypeList.size() != travelingTypeIds.size())
            throw new DataValidationException("TravelingType ids are wrong!");

        ResortCity city = getEntity(id);
        city.getTravelingTypes().clear();
        city.getTravelingTypes().addAll(travelingTypeList);
        return ResortCityDTO.makeDTO(repository.save(city));
    }

    @Override
    public void deleteTravelingType(Long id, Long typeId) {
        TravelingType travelingType = travelingTypeService.getEntity(typeId);
        ResortCity city = getEntity(id);
        city.getTravelingTypes().remove(travelingType);
        repository.save(city);
    }



    private void setInputData(final ResortCity city, ResortCityEditDTO cityEditDTO) {
        city.setName(cityEditDTO.getName())
                .setIsAbleForEntering(cityEditDTO.getIsAbleForEntering());
    }

}
