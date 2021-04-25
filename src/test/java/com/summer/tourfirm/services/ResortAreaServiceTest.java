package com.summer.tourfirm.services;

import com.summer.tourfirm.dto.ResortAreaDTO;
import com.summer.tourfirm.dto.edit.ResortAreaEditDTO;
import com.summer.tourfirm.entity.ResortArea;
import com.summer.tourfirm.entity.ResortCity;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.repository.ResortAreaRepository;
import com.summer.tourfirm.service.impl.ResortCityServiceImpl;
import com.summer.tourfirm.service.impl.ResortAreaServiceImpl;
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
public class ResortAreaServiceTest {

    @InjectMocks
    private ResortAreaServiceImpl resortAreaService;

    @Mock
    private ResortCityServiceImpl resortCityService;

    @Mock
    private ResortAreaRepository resortAreaRepository;

    @Test
    public void whenGetResortArea_thenReturnResortAreaList() {
        ResortArea resortArea = new ResortArea()
                .setName("name");

        ResortAreaDTO resortAreaDTO = ResortAreaDTO.makeSimpleDTO(resortArea);
        when(resortAreaRepository.findByOrderByIdAsc()).thenReturn(Arrays.asList(resortArea));

        List<ResortAreaDTO> resortAreaDTOList = resortAreaService.getAll();

        assertThat(resortAreaDTOList).hasSize(1);
        assertThat(resortAreaDTOList.get(0)).isEqualToComparingFieldByField(resortAreaDTO);
    }

    @Test
    public void whenGetResortAreaById_thenReturnOneResortArea() {
        ResortArea resortArea = new ResortArea()
                .setId(1L)
                .setName("name");

        ResortAreaDTO resortAreaDTO = ResortAreaDTO.makeSimpleDTO(resortArea);
        when(resortAreaRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(resortArea));

        ResortAreaDTO foundDTO = resortAreaService.get(resortAreaDTO.getId());

        assertThat(foundDTO).isEqualToComparingFieldByField(resortAreaDTO);
    }

    @Test
    public void whenGetResortAreaByIdIsNotExist_thenThrowDataNotFoundException() {
        when(resortAreaRepository.findById(anyLong())).thenThrow(DataNotFoundException.class);
        Exception exception = assertThrows(DataNotFoundException.class, () -> {
            resortAreaService.get(1L);
        });

    }

    @Test
    public void whenCreateResortArea_thenReturnResortAreaDTO() {
        ResortCity resortCity = new ResortCity()
                .setId(1L)
                .setIsAbleForEntering(false)
                .setName("name");

        ResortAreaEditDTO resortCityEditDTO = new ResortAreaEditDTO();
        resortCityEditDTO.setName("name");
        resortCityEditDTO.setCityId(resortCity.getId());

        when(resortCityService.getEntity(anyLong())).thenReturn((resortCity));

        when(resortCityService.save(any())).thenReturn(resortCity);

        ResortAreaDTO resortAreaDTO = resortAreaService.create(resortCityEditDTO);

        assertThat(resortAreaDTO.getName()).isSameAs(resortCityEditDTO.getName());
    }

    @Test
    public void whenUpdateResortArea_thenReturnResortAreaDTO() {
        ResortCity resortCity = new ResortCity()
                .setId(1L)
                .setName("Name")
                .setIsAbleForEntering(true);

        ResortAreaEditDTO resortAreaEditDTO = new ResortAreaEditDTO();
        resortAreaEditDTO.setId(1L);
        resortAreaEditDTO.setName("name");
        resortAreaEditDTO.setCityId(resortCity.getId());

        ResortArea resortArea = new ResortArea()
                .setId(resortAreaEditDTO.getId())
                .setName(resortAreaEditDTO.getName())
                .setCity(resortCity);

        when(resortAreaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(resortArea));

        when(resortAreaRepository.save(any())).thenReturn(resortArea);

        ResortAreaDTO resortAreaDTO = resortAreaService.update(resortAreaEditDTO);

        assertThat(resortAreaDTO.getName()).isSameAs(resortAreaEditDTO.getName());
    }

    @Test
    public void whenDeleteResortArea_thenVoid() {
        ResortCity resortCity = new ResortCity()
                .setId(1L)
                .setName("Name")
                .setIsAbleForEntering(true);

        ResortArea resortArea = new ResortArea()
                .setId(1L)
                .setName("name")
                .setCity(resortCity);

        when(resortAreaRepository.findById(anyLong())).thenReturn(Optional.of(resortArea));

        when(resortCityService.save(any())).thenReturn(resortCity);

        doNothing().when(resortAreaRepository).delete(any());
        resortAreaService.delete(resortArea.getId());

        verify(resortAreaRepository).delete(any());

    }
}
