package com.summer.tourfirm.services;


import com.summer.tourfirm.dto.CountryDTO;
import com.summer.tourfirm.dto.edit.CountryEditDTO;
import com.summer.tourfirm.entity.Country;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.repository.CountryRepository;
import com.summer.tourfirm.service.ICountryService;
import com.summer.tourfirm.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @InjectMocks
    private CountryServiceImpl service;

    @Mock
    private CountryRepository repository;

    @Test
    public void whenGetCountries_thenReturnCountryList() {
        Country country = new Country()
                .setAbleForEntering(false)
                .setName("Temp Country For Tests");

        CountryDTO countryDTO = CountryDTO.makeSimpleDTO(country);
        when(repository.findByOrderByIdAsc()).thenReturn(Arrays.asList(country));

        List<CountryDTO> countryDTOList = service.getAll();

        assertThat(countryDTOList).hasSize(1);
        assertThat(countryDTOList.get(0)).isEqualToComparingFieldByField(countryDTO);
    }

    @Test
    public void whenGetCountryById_thenReturnOneCountry() {
        Country country = new Country()
                .setId(1L)
                .setAbleForEntering(false)
                .setName("Temp Country For Tests");

        CountryDTO countryDTO = CountryDTO.makeSimpleDTO(country);
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(country));

        CountryDTO foundDTO = service.get(countryDTO.getId());

        assertThat(foundDTO).isEqualToComparingFieldByField(countryDTO);
    }

    @Test
    public void whenGetCountryByIdIsNotExist_thenThrowDataNotFoundException() {
        when(repository.findById(anyLong())).thenThrow(DataNotFoundException.class);
        Exception exception = assertThrows(DataNotFoundException.class, () -> {
            service.get(1L);
        });

    }

    @Test
    public void whenCreateCountry_thenReturnCountryDTO() {
        CountryEditDTO countryEditDTO = new CountryEditDTO();
        countryEditDTO.setName("Test Country Name");
        countryEditDTO.setIsAbleForEntering(true);

        Country country = new Country()
                .setName(countryEditDTO.getName())
                .setAbleForEntering(countryEditDTO.getIsAbleForEntering());

        when(repository.save(any())).thenReturn(country);

        CountryDTO countryDTO = service.create(countryEditDTO);

        assertThat(countryDTO.getName()).isSameAs(countryEditDTO.getName());
    }

    @Test
    public void whenUpdateCountry_thenReturnCountryDTO() {
        CountryEditDTO countryEditDTO = new CountryEditDTO();
        countryEditDTO.setId(1L);
        countryEditDTO.setName("Test Country Name");
        countryEditDTO.setIsAbleForEntering(true);

        Country country = new Country()
                .setId(countryEditDTO.getId())
                .setName(countryEditDTO.getName())
                .setAbleForEntering(countryEditDTO.getIsAbleForEntering());

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(country));

        when(repository.save(any())).thenReturn(country);

        CountryDTO countryDTO = service.update(countryEditDTO);

        assertThat(countryDTO.getName()).isSameAs(countryEditDTO.getName());
    }

    @Test
    public void whenDeleteCountry_thenVoid() {
        Country country = new Country()
                .setId(1L)
                .setName("Test Country Name")
                .setAbleForEntering(true);

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(country));
        doNothing().when(repository).delete(any());
        service.delete(country.getId());

        verify(repository).delete(any());
    }
}
