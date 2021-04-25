package com.summer.tourfirm.services;

import com.summer.tourfirm.dto.ReservedApartmentDTO;
import com.summer.tourfirm.dto.edit.ReservedApartmentEditDTO;
import com.summer.tourfirm.entity.Apartment;
import com.summer.tourfirm.entity.ReservedApartment;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.repository.ReservedApartmentRepository;
import com.summer.tourfirm.service.impl.ApartmentServiceImpl;
import com.summer.tourfirm.service.impl.ReservedApartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.ZONED_DATE_TIME;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservedApartmentServiceTest {

    @InjectMocks
    private ReservedApartmentServiceImpl reservedApartmentService;

    @Mock
    private ApartmentServiceImpl apartmentService;

    @Mock
    private ReservedApartmentRepository reservedApartmentRepository;

    @Test
    public void whenGetReservedApartment_thenReturnReservedApartmentList() {
        ReservedApartment reservedApartment = new ReservedApartment()
                .setDateIn(ZonedDateTime.now())
                .setDateOut(ZonedDateTime.now());

        ReservedApartmentDTO reservedApartmentDTO = ReservedApartmentDTO.makeSimpleDTO(reservedApartment);
        when(reservedApartmentRepository.findByOrderByIdAsc()).thenReturn(Arrays.asList(reservedApartment));

        List<ReservedApartmentDTO> reservedApartmentDTOList = reservedApartmentService.getAll();

        assertThat(reservedApartmentDTOList).hasSize(1);
        assertThat(reservedApartmentDTOList.get(0)).isEqualToComparingFieldByField(reservedApartmentDTO);
    }

    @Test
    public void whenGetReservedApartmentById_thenReturnOneReservedApartment() {
        ReservedApartment reservedApartment = new ReservedApartment()
                .setId(1L)
                .setDateIn(ZonedDateTime.now())
                .setDateOut(ZonedDateTime.now());

        ReservedApartmentDTO reservedApartmentDTO = ReservedApartmentDTO.makeSimpleDTO(reservedApartment);
        when(reservedApartmentRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(reservedApartment));

        ReservedApartmentDTO foundDTO = reservedApartmentService.get(reservedApartmentDTO.getId());

        assertThat(foundDTO).isEqualToComparingFieldByField(reservedApartmentDTO);
    }

    @Test
    public void whenGetReservedApartmentByIdIsNotExist_thenThrowDataNotFoundException() {
        when(reservedApartmentRepository.findById(anyLong())).thenThrow(DataNotFoundException.class);
        Exception exception = assertThrows(DataNotFoundException.class, () -> {
            reservedApartmentService.get(1L);
        });

    }

    @Test
    public void whenCreateReservedApartment_thenReturnReservedApartmentDTO() {
        Apartment apartment = new Apartment()
                .setId(1L)
                .setNumber("number")
                .setPrice(123)
                .setAmountOfBeds(1)
                .setAmountOfRooms(1)
                .setIfBathroomExist(true);

        ReservedApartmentEditDTO reservedApartmentEditDTO = new ReservedApartmentEditDTO();
        reservedApartmentEditDTO.setDateIn(ZonedDateTime.now());
        reservedApartmentEditDTO.setDateOut(ZonedDateTime.now());
        reservedApartmentEditDTO.setApartmentId(apartment.getId());

        when(apartmentService.getEntity(anyLong())).thenReturn((apartment));

        when(apartmentService.save((Apartment) any())).thenReturn(apartment);

        ReservedApartmentDTO reservedApartmentDTO = reservedApartmentService.create(reservedApartmentEditDTO);

        assertThat(reservedApartmentDTO.getId()).isSameAs(reservedApartmentEditDTO.getId());
    }

    @Test
    public void whenUpdateReservedApartment_thenReturnReservedApartmentDTO() {
        Apartment apartment = new Apartment()
                .setId(1L)
                .setNumber("number")
                .setPrice(123)
                .setAmountOfBeds(1)
                .setAmountOfRooms(1)
                .setIfBathroomExist(true);

        ReservedApartmentEditDTO reservedApartmentEditDTO = new ReservedApartmentEditDTO();
        reservedApartmentEditDTO.setId(1L);
        reservedApartmentEditDTO.setDateIn(ZonedDateTime.now());
        reservedApartmentEditDTO.setDateOut(ZonedDateTime.now());
        reservedApartmentEditDTO.setApartmentId(apartment.getId());

        ReservedApartment reservedApartment = new ReservedApartment()
                .setId(reservedApartmentEditDTO.getId())
                .setDateIn(reservedApartmentEditDTO.getDateIn())
                .setDateOut(reservedApartmentEditDTO.getDateOut())
                .setApartment(apartment);

        when(reservedApartmentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(reservedApartment));

        when(reservedApartmentRepository.save(any())).thenReturn(reservedApartment);

        ReservedApartmentDTO reservedApartmentDTO = reservedApartmentService.update(reservedApartmentEditDTO);

        assertThat(reservedApartmentDTO.getId()).isSameAs(reservedApartmentEditDTO.getId());
    }

    @Test
    public void whenDeleteReservedApartment_thenVoid() {
        Apartment apartment = new Apartment()
                .setId(1L)
                .setNumber("number")
                .setPrice(123)
                .setAmountOfBeds(1)
                .setAmountOfRooms(1)
                .setIfBathroomExist(true);

        ReservedApartment reservedApartment = new ReservedApartment()
                .setId(1L)
                .setDateIn(ZonedDateTime.now())
                .setDateOut(ZonedDateTime.now())
                .setApartment(apartment);

        when(reservedApartmentRepository.findById(anyLong())).thenReturn(Optional.of(reservedApartment));

        when(apartmentService.save((Apartment) any())).thenReturn(apartment);

        doNothing().when(reservedApartmentRepository).delete(any());
        reservedApartmentService.delete(reservedApartment.getId());

        verify(reservedApartmentRepository).delete(any());

    }
}
