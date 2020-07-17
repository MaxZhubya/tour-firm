package com.summer.tourfirm.service.impl;

import com.summer.tourfirm.dto.CountryDTO;
import com.summer.tourfirm.dto.edit.CountryEditDTO;
import com.summer.tourfirm.entity.Country;
import com.summer.tourfirm.entity.ResortCity;
import com.summer.tourfirm.entity.enums.EntranceWay;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.exception.DataValidationException;
import com.summer.tourfirm.repository.CountryRepository;
import com.summer.tourfirm.service.ICountryService;
import com.summer.tourfirm.service.IResortCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class CountryServiceImpl implements ICountryService {

    @Autowired
    private IResortCityService cityService;

    @Autowired
    private CountryRepository repository;



    @Override
    @Transactional(readOnly = true)
    public CountryDTO get(Long id) {
        return CountryDTO.makeDTO(getEntity(id));
    }


    @Override
    @Transactional(readOnly = true)
    public List<CountryDTO> getAll() {
        return repository.findByOrderByIdAsc().stream()
                .map(CountryDTO::makeDTO).collect(Collectors.toList());
    }


    @Override
    public CountryDTO create(CountryEditDTO countryEditDTO) {
        Country country = new Country()
                .setAbleForEntering(countryEditDTO.getAbleForEntering())
                .setEnterWays(countryEditDTO.getEnterWays());

        country = repository.save(country);

        setInputData(country, countryEditDTO);

        return CountryDTO.makeDTO(repository.save(country));
    }


    @Override
    public CountryDTO update(CountryEditDTO countryEditDTO) {
        if (Objects.isNull(countryEditDTO.getId()))
            throw new DataValidationException("ID can't be null!");

        Country country = getEntity(countryEditDTO.getId());

        clearRelatedData(country);

        setInputData(country, countryEditDTO);

        return CountryDTO.makeDTO(repository.save(country));
    }


    @Override
    public void delete(Long id) {
        Country country = getEntity(id);

        clearRelatedData(country);

        repository.delete(country);
    }


    @Override
    public Country save(Country country) {
        return repository.save(country);
    }


    @Override
    public Country getEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Country with id: "
                + id.toString() + " is not existed"));
    }


    @Override
    public List<Country> getEntitiesByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }


    private void setInputData(final Country country, CountryEditDTO countryEditDTO) {

        // Set ResortCity
        if (!countryEditDTO.getCityIds().isEmpty()) {
            List<ResortCity> cityList = cityService.getEntitiesByIds(countryEditDTO.getCityIds());
            if (cityList.size() != countryEditDTO.getCityIds().size())
                throw new DataValidationException("ResortCity ids are wrong!");
            cityList.forEach(resortCity -> resortCity.setCountry(country));
            country.setCities(cityList);
        }

        // Set Able ForEntering
        country.setAbleForEntering(countryEditDTO.getAbleForEntering());

        // Set EnterWays
        if (!countryEditDTO.getEnterWays().isEmpty()) {
            if (country.getEnterWays().size() != countryEditDTO.getEnterWays().size())
                throw new DataValidationException("Wrong types!");
           country.setEnterWays(countryEditDTO.getEnterWays());
        }


    }

    private void clearRelatedData(Country country) {
        List<ResortCity> cities = country.getCities();
        if (!country.getCities().isEmpty()) {
            country.getCities().removeAll(cities);
        }
    }

}
