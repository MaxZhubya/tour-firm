package com.summer.tourfirm.service;

import com.summer.tourfirm.dto.CountryDTO;
import com.summer.tourfirm.dto.edit.CountryEditDTO;
import com.summer.tourfirm.entity.Country;

import java.util.List;

public interface ICountryService {

    CountryDTO get(Long id);      // Read
    List<CountryDTO> getAll();    // Read
    CountryDTO create(CountryEditDTO countryEditDTO);   // Create
    CountryDTO update(CountryEditDTO countryEditDTO);   // Update
    void delete(Long id);   // Delete
    Country save(Country country);
    Country getEntity(Long id);
    List<Country> getEntitiesByIds(List<Long> ids);

    /* EntranceType methods */
    CountryDTO addEntranceType(Long id, List<Long> entranceTypeIds);
    void deleteEntranceType(Long id, Long typeId);
}
