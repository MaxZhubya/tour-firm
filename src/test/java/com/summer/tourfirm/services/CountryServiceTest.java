package com.summer.tourfirm.services;


import com.summer.tourfirm.dto.CountryDTO;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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
    public void whenGetCountryByIdIsNotExist_thenThrowDataNotFoundException() {
        when(repository.findById(anyLong())).thenThrow(DataNotFoundException.class);
        Exception exception = assertThrows(DataNotFoundException.class, () -> {
            service.get(1l);
        });

    }

}
