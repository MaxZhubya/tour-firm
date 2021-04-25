package com.summer.tourfirm.services;

import com.summer.tourfirm.dto.ApartmentDTO;
import com.summer.tourfirm.dto.edit.ApartmentEditDTO;
import com.summer.tourfirm.entity.Apartment;
import com.summer.tourfirm.entity.LiveBuilding;
import com.summer.tourfirm.entity.enums.BuildingEnum;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.repository.ApartmentRepository;
import com.summer.tourfirm.service.impl.LiveBuildingServiceImpl;
import com.summer.tourfirm.service.impl.ApartmentServiceImpl;
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
public class ApartmentServiceTest {

    @InjectMocks
    private ApartmentServiceImpl apartmentService;

    @Mock
    private LiveBuildingServiceImpl liveBuildingService;

    @Mock
    private ApartmentRepository apartmentRepository;

    @Test
    public void whenGetApartment_thenReturnApartmentList() {
        Apartment apartment = new Apartment()
                .setNumber("number")
                .setPrice(123)
                .setAmountOfBeds(1)
                .setAmountOfRooms(1)
                .setIfBathroomExist(true);

        ApartmentDTO apartmentDTO = ApartmentDTO.makeSimpleDTO(apartment);
        when(apartmentRepository.findByOrderByIdAsc()).thenReturn(Arrays.asList(apartment));

        List<ApartmentDTO> resortCityDTOList = apartmentService.getAll();

        assertThat(resortCityDTOList).hasSize(1);
        assertThat(resortCityDTOList.get(0)).isEqualToComparingFieldByField(apartmentDTO);
    }

    @Test
    public void whenGetApartmentById_thenReturnOneApartment() {
        Apartment apartment = new Apartment()
                .setId(1L)
                .setNumber("number")
                .setPrice(123)
                .setAmountOfBeds(1)
                .setAmountOfRooms(1)
                .setIfBathroomExist(true);

        ApartmentDTO apartmentDTO = ApartmentDTO.makeSimpleDTO(apartment);
        when(apartmentRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(apartment));

        ApartmentDTO foundDTO = apartmentService.get(apartmentDTO.getId());

        assertThat(foundDTO).isEqualToComparingFieldByField(apartmentDTO);
    }

    @Test
    public void whenGetApartmentByIdIsNotExist_thenThrowDataNotFoundException() {
        when(apartmentRepository.findById(anyLong())).thenThrow(DataNotFoundException.class);
        Exception exception = assertThrows(DataNotFoundException.class, () -> {
            apartmentService.get(1L);
        });

    }

    @Test
    public void whenCreateApartment_thenReturnApartmentDTO() {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setId(1L)
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        ApartmentEditDTO apartmentEditDTO = new ApartmentEditDTO();
        apartmentEditDTO.setNumber("number");
        apartmentEditDTO.setPrice(123);
        apartmentEditDTO.setAmountOfBeds(1);
        apartmentEditDTO.setAmountOfRooms(1);
        apartmentEditDTO.setIfBathroomExist(true);
        apartmentEditDTO.setBuildingId(liveBuilding.getId());

        when(liveBuildingService.getEntity(anyLong())).thenReturn((liveBuilding));

        when(liveBuildingService.save((LiveBuilding) any())).thenReturn(liveBuilding);

        ApartmentDTO apartmentDTO = apartmentService.create(apartmentEditDTO);

        assertThat(apartmentDTO.getNumber()).isSameAs(apartmentEditDTO.getNumber());
    }

    @Test
    public void whenUpdateApartment_thenReturnApartmentDTO() {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setId(1L)
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        ApartmentEditDTO apartmentEditDTO = new ApartmentEditDTO();
        apartmentEditDTO.setId(1L);
        apartmentEditDTO.setNumber("number");
        apartmentEditDTO.setPrice(123);
        apartmentEditDTO.setAmountOfBeds(1);
        apartmentEditDTO.setAmountOfRooms(1);
        apartmentEditDTO.setIfBathroomExist(true);
        apartmentEditDTO.setBuildingId(liveBuilding.getId());

        Apartment apartment = new Apartment()
                .setId(apartmentEditDTO.getId())
                .setNumber(apartmentEditDTO.getNumber())
                .setPrice(apartmentEditDTO.getPrice())
                .setAmountOfBeds(apartmentEditDTO.getAmountOfBeds())
                .setAmountOfRooms(apartmentEditDTO.getAmountOfRooms())
                .setIfBathroomExist(apartmentEditDTO.getIfBathroomExist())
                .setBuilding(liveBuilding);

        when(apartmentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(apartment));

        when(apartmentRepository.save(any())).thenReturn(apartment);

        ApartmentDTO apartmentDTO = apartmentService.update(apartmentEditDTO);

        assertThat(apartmentDTO.getNumber()).isSameAs(apartmentEditDTO.getNumber());
    }

    @Test
    public void whenDeleteApartment_thenVoid() {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setId(1L)
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        Apartment apartment = new Apartment()
                .setId(1L)
                .setNumber("number")
                .setPrice(123)
                .setAmountOfBeds(1)
                .setAmountOfRooms(1)
                .setIfBathroomExist(true)
                .setBuilding(liveBuilding);

        when(apartmentRepository.findById(anyLong())).thenReturn(Optional.of(apartment));

        when(liveBuildingService.save((LiveBuilding) any())).thenReturn(liveBuilding);

        doNothing().when(apartmentRepository).delete(any());
        apartmentService.delete(apartment.getId());

        verify(apartmentRepository).delete(any());

    }
}
