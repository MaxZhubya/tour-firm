package com.summer.tourfirm.service.impl;

import com.summer.tourfirm.dto.CountryDTO;
import com.summer.tourfirm.dto.edit.CountryEditDTO;
import com.summer.tourfirm.entity.Country;
import com.summer.tourfirm.entity.types.EntranceType;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.exception.DataValidationException;
import com.summer.tourfirm.repository.CountryRepository;
import com.summer.tourfirm.service.ICountryService;
import com.summer.tourfirm.service.IEntranceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class CountryServiceImpl implements ICountryService {

    @Autowired
    private IEntranceTypeService entranceTypeService;

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
        List<CountryDTO> countryDTOList = new ArrayList<>();
        repository.findByOrderByIdAsc().forEach(value -> countryDTOList.add(CountryDTO.makeDTO(value)));
        return countryDTOList;
    }

    @Override
    public CountryDTO create(CountryEditDTO countryEditDTO) {
        Country country = new Country();
        setInputData(country, countryEditDTO);
        return CountryDTO.makeDTO(repository.save(country));
    }

    @Override
    public CountryDTO update(CountryEditDTO countryEditDTO) {
        if (Objects.isNull(countryEditDTO.getId()))
            throw new DataValidationException("ID can't be null!");

        Country country = getEntity(countryEditDTO.getId());
        setInputData(country, countryEditDTO);
        return CountryDTO.makeDTO(repository.save(country));
    }

    @Override
    public void delete(Long id) {
        Country country = getEntity(id);
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

    @Override
    public CountryDTO addEntranceType(Long id, List<Long> entranceTypeIds) {
        List<EntranceType> entranceTypeList = entranceTypeService.getEntitiesByIds(entranceTypeIds);
        if (entranceTypeList.size() != entranceTypeIds.size())
            throw new DataValidationException("EntranceType ids are wrong!");

        Country country = getEntity(id);
        country.getEnterTypes().clear();
        country.getEnterTypes().addAll(entranceTypeList);
        return CountryDTO.makeDTO(repository.save(country));
    }

    @Override
    public void deleteEntranceType(Long id, Long typeId) {
        EntranceType entranceType = entranceTypeService.getEntity(typeId);
        Country country = getEntity(id);
        country.getEnterTypes().remove(entranceType);
        repository.save(country);
    }

    private void setInputData(final Country country, CountryEditDTO countryEditDTO) {
        country.setAbleForEntering(countryEditDTO.getIsAbleForEntering())
                .setName(countryEditDTO.getName());
    }

}
