package com.summer.tourfirm.services;

import com.summer.tourfirm.dto.LiveBuildingDTO;
import com.summer.tourfirm.dto.edit.LiveBuildingEditDTO;
import com.summer.tourfirm.entity.LiveBuilding;
import com.summer.tourfirm.entity.ResortArea;
import com.summer.tourfirm.entity.enums.BuildingEnum;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.repository.LiveBuildingRepository;
import com.summer.tourfirm.service.impl.LiveBuildingServiceImpl;
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
public class LiveBuildingServiceTest {

    @InjectMocks
    private LiveBuildingServiceImpl liveBuildingService;

    @Mock
    private ResortAreaServiceImpl resortAreaService;

    @Mock
    private LiveBuildingRepository liveBuildingRepository;

    @Test
    public void whenGetLiveBuilding_thenReturnLiveBuildingList() {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        LiveBuildingDTO liveBuildingDTO = LiveBuildingDTO.makeSimpleDTO(liveBuilding);
        when(liveBuildingRepository.findByOrderByIdAsc()).thenReturn(Arrays.asList(liveBuilding));

        List<LiveBuildingDTO> liveBuildingDTOList = liveBuildingService.getAll();

        assertThat(liveBuildingDTOList).hasSize(1);
        assertThat(liveBuildingDTOList.get(0)).isEqualToComparingFieldByField(liveBuildingDTO);
    }

    @Test
    public void whenGetLiveBuildingById_thenReturnOneLiveBuilding() {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setId(1L)
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        LiveBuildingDTO liveBuildingDTO = LiveBuildingDTO.makeSimpleDTO(liveBuilding);
        when(liveBuildingRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(liveBuilding));

        LiveBuildingDTO foundDTO = liveBuildingService.get(liveBuildingDTO.getId());

        assertThat(foundDTO).isEqualToComparingFieldByField(liveBuildingDTO);
    }

    @Test
    public void whenGetLiveBuildingByIdIsNotExist_thenThrowDataNotFoundException() {
        when(liveBuildingRepository.findById(anyLong())).thenThrow(DataNotFoundException.class);
        Exception exception = assertThrows(DataNotFoundException.class, () -> {
            liveBuildingService.get(1L);
        });

    }

    @Test
    public void whenCreateLiveBuilding_thenReturnLiveBuildingDTO() {
        ResortArea area = new ResortArea()
                .setId(1L)
                .setName("name");

        LiveBuildingEditDTO liveBuildingEditDTO = new LiveBuildingEditDTO();
        liveBuildingEditDTO.setType(BuildingEnum.HOTEL);
        liveBuildingEditDTO.setNumber("Number");
        liveBuildingEditDTO.setAddress("Address");
        liveBuildingEditDTO.setName("Name");
        liveBuildingEditDTO.setIfPoolExist(true);
        liveBuildingEditDTO.setIfParkingExist(true);
        liveBuildingEditDTO.setDistanceToBeach(123);
        liveBuildingEditDTO.setAreaId(area.getId());

        when(resortAreaService.getEntity(anyLong())).thenReturn((area));

        when(resortAreaService.save((ResortArea) any())).thenReturn(area);

        LiveBuildingDTO liveBuildingDTO = liveBuildingService.create(liveBuildingEditDTO);

        assertThat(liveBuildingDTO.getName()).isSameAs(liveBuildingEditDTO.getName());
    }

    @Test
    public void whenUpdateLiveBuilding_thenReturnLiveBuildingDTO() {
        ResortArea area = new ResortArea()
                .setId(1L)
                .setName("Name");

        LiveBuildingEditDTO liveBuildingEditDTO = new LiveBuildingEditDTO();
        liveBuildingEditDTO.setId(1L);
        liveBuildingEditDTO.setType(BuildingEnum.HOTEL);
        liveBuildingEditDTO.setNumber("Number");
        liveBuildingEditDTO.setAddress("Address");
        liveBuildingEditDTO.setName("Name");
        liveBuildingEditDTO.setIfPoolExist(true);
        liveBuildingEditDTO.setIfParkingExist(true);
        liveBuildingEditDTO.setDistanceToBeach(123);
        liveBuildingEditDTO.setAreaId(area.getId());

        LiveBuilding liveBuilding = new LiveBuilding()
                .setId(liveBuildingEditDTO.getId())
                .setType(liveBuildingEditDTO.getType())
                .setNumber(liveBuildingEditDTO.getNumber())
                .setAddress(liveBuildingEditDTO.getAddress())
                .setName(liveBuildingEditDTO.getName())
                .setIfPoolExist(liveBuildingEditDTO.getIfPoolExist())
                .setIfParkingExist(liveBuildingEditDTO.getIfParkingExist())
                .setDistanceToBeach(liveBuildingEditDTO.getDistanceToBeach())
                .setArea(area);

        when(liveBuildingRepository.findById(anyLong())).thenReturn(Optional.ofNullable(liveBuilding));

        when(liveBuildingRepository.save(any())).thenReturn(liveBuilding);

        LiveBuildingDTO liveBuildingDTO = liveBuildingService.update(liveBuildingEditDTO);

        assertThat(liveBuildingDTO.getName()).isSameAs(liveBuildingEditDTO.getName());
    }

    @Test
    public void whenDeleteLiveBuilding_thenVoid() {
        ResortArea resortArea = new ResortArea()
                .setId(1L)
                .setName("Name");

        LiveBuilding liveBuilding = new LiveBuilding()
                .setId(1L)
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name")
                .setArea(resortArea);

        when(liveBuildingRepository.findById(anyLong())).thenReturn(Optional.of(liveBuilding));

        when(resortAreaService.save((ResortArea) any())).thenReturn(resortArea);

        doNothing().when(liveBuildingRepository).delete(any());
        liveBuildingService.delete(liveBuilding.getId());

        verify(liveBuildingRepository).delete(any());

    }
}
