package com.summer.tourfirm.services;

import com.summer.tourfirm.dto.ResortCityDTO;
import com.summer.tourfirm.dto.edit.ResortCityEditDTO;
import com.summer.tourfirm.entity.Country;
import com.summer.tourfirm.entity.ResortCity;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.repository.ResortCityRepository;
import com.summer.tourfirm.service.impl.CountryServiceImpl;
import com.summer.tourfirm.service.impl.ResortCityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResortCityServiceTest {

    @InjectMocks
    private ResortCityServiceImpl resortCityService;

    @Mock
    private CountryServiceImpl countryService;

    @Mock
    private ResortCityRepository resortCityRepository;

    @Test
    public void whenGetResortCity_thenReturnResortCityList() {
        ResortCity resortCity = new ResortCity()
                .setIsAbleForEntering(false)
                .setName("Temp City For Tests");

        ResortCityDTO resortCityDTO = ResortCityDTO.makeSimpleDTO(resortCity);
        when(resortCityRepository.findByOrderByIdAsc()).thenReturn(Arrays.asList(resortCity));

        List<ResortCityDTO> resortCityDTOList = resortCityService.getAll();

        assertThat(resortCityDTOList).hasSize(1);
        assertThat(resortCityDTOList.get(0)).isEqualToComparingFieldByField(resortCityDTO);
    }

    @Test
    public void whenGetResortCityById_thenReturnOneResortCity() {
        ResortCity resortCity = new ResortCity()
                .setId(1L)
                .setIsAbleForEntering(false)
                .setName("Temp City For Tests");

        ResortCityDTO resortCityDTO = ResortCityDTO.makeSimpleDTO(resortCity);
        when(resortCityRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(resortCity));

        ResortCityDTO foundDTO = resortCityService.get(resortCityDTO.getId());

        assertThat(foundDTO).isEqualToComparingFieldByField(resortCityDTO);
    }

    @Test
    public void whenGetResortCityByIdIsNotExist_thenThrowDataNotFoundException() {
        when(resortCityRepository.findById(anyLong())).thenThrow(DataNotFoundException.class);
        Exception exception = assertThrows(DataNotFoundException.class, () -> {
            resortCityService.get(1L);
        });

    }

    @Test
    public void whenCreateResortCity_thenReturnResortCityDTO() {
        Country country = new Country()
                .setId(1L)
                .setAbleForEntering(false)
                .setName("Temp Country For Tests");

        ResortCityEditDTO resortCityEditDTO = new ResortCityEditDTO();
        resortCityEditDTO.setName("Test City Name");
        resortCityEditDTO.setIsAbleForEntering(true);
        resortCityEditDTO.setCountryId(country.getId());

        when(countryService.getEntity(anyLong())).thenReturn((country));

        when(countryService.save(any())).thenReturn(country);

        ResortCityDTO resortCityDTO = resortCityService.create(resortCityEditDTO);

        assertThat(resortCityDTO.getName()).isSameAs(resortCityEditDTO.getName());
    }

    @Test
    public void whenUpdateResortCity_thenReturnResortCityDTO() {
        Country country = new Country()
                .setId(1L)
                .setName("Name")
                .setAbleForEntering(true);

        ResortCityEditDTO resortCityEditDTO = new ResortCityEditDTO();
        resortCityEditDTO.setId(1L);
        resortCityEditDTO.setName("Test City Name");
        resortCityEditDTO.setIsAbleForEntering(true);
        resortCityEditDTO.setCountryId(country.getId());

        ResortCity resortCity = new ResortCity()
                .setId(resortCityEditDTO.getId())
                .setName(resortCityEditDTO.getName())
                .setIsAbleForEntering(resortCityEditDTO.getIsAbleForEntering())
                .setCountry(country);

        when(resortCityRepository.findById(anyLong())).thenReturn(Optional.ofNullable(resortCity));

        when(resortCityRepository.save(any())).thenReturn(resortCity);

        ResortCityDTO resortCityDTO = resortCityService.update(resortCityEditDTO);

        assertThat(resortCityDTO.getName()).isSameAs(resortCityEditDTO.getName());
    }

    @Test
    public void whenDeleteResortCity_thenVoid() {
        Country country = new Country()
                .setId(1L)
                .setName("Name")
                .setAbleForEntering(true);

        ResortCity resortCity = new ResortCity()
                .setId(1L)
                .setIsAbleForEntering(false)
                .setName("Temp City For Tests")
                .setCountry(country);

        when(resortCityRepository.findById(anyLong())).thenReturn(Optional.of(resortCity));

        when(countryService.save(any())).thenReturn(country);

        doNothing().when(resortCityRepository).delete(any());
        resortCityService.delete(resortCity.getId());

        verify(resortCityRepository).delete(any());

    }
}
