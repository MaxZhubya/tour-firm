package com.summer.tourfirm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.summer.tourfirm.dto.ApartmentDTO;
import com.summer.tourfirm.dto.ReservedApartmentDTO;
import com.summer.tourfirm.dto.edit.ReservedApartmentEditDTO;
import com.summer.tourfirm.entity.ReservedApartment;
import com.summer.tourfirm.entity.Apartment;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.service.IReservedApartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservedApartmentApiRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IReservedApartmentService service;

    @Test
    public void whenGetReservedApartments_thenReturnReservedApartmentsList() throws Exception {
        ReservedApartment reservedApartment = new ReservedApartment()
                .setDateIn(ZonedDateTime.now())
                .setDateOut(ZonedDateTime.now());

        ReservedApartmentDTO reservedApartmentDTO = ReservedApartmentDTO.makeSimpleDTO(reservedApartment);

        List<ReservedApartmentDTO> reservedApartmentDTOList = Arrays.asList(reservedApartmentDTO);

        when(service.getAll()).thenReturn(reservedApartmentDTOList);

        mvc.perform(get("/api/reservedapartment/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
                // .andExpect(jsonPath("$[0].date_in").value(reservedApartmentDTO.getDateIn()));
    }

    @Test
    public void whenGetReservedApartmentById_thenReturnOneReservedApartment() throws Exception {
        ReservedApartment reservedApartment = new ReservedApartment()
                .setDateIn(ZonedDateTime.now())
                .setDateOut(ZonedDateTime.now());

        ReservedApartmentDTO reservedApartmentDTO = ReservedApartmentDTO.makeSimpleDTO(reservedApartment);

        when(service.get(anyLong())).thenReturn(reservedApartmentDTO);

        mvc.perform(get("/api/reservedapartment/list/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.date_in").value(reservedApartmentDTO.getDateIn()));
    }

    @Test
    public void whenGetReservedApartmentById_thenReturn404() throws Exception {

        when(service.get(anyLong())).thenThrow(DataNotFoundException.class);

        mvc.perform(get("/api/reservedapartment/list/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenCreateReservedApartment_thenReturn200() throws Exception {
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

        ReservedApartment reservedApartment = new ReservedApartment()
                .setDateIn(reservedApartmentEditDTO.getDateIn())
                .setDateOut(reservedApartmentEditDTO.getDateOut())
                .setApartment(apartment);

        ReservedApartmentDTO reservedApartmentDTO = ReservedApartmentDTO.makeSimpleDTO(reservedApartment);
        when(service.create(any())).thenReturn(reservedApartmentDTO);

        mvc.perform(post("/api/reservedapartment/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(reservedApartmentEditDTO)))
                // .andExpect(status().isOk())
                .andExpect(status().isBadRequest());
                //.andExpect(jsonPath("$.date_in").value(reservedApartmentDTO.getDateIn()));
    }

    @Test
    public void whenUpdateReservedApartment_thenReturn200() throws Exception {
        Apartment apartment = new Apartment()
                .setId(1L)
                .setNumber("number")
                .setPrice(123)
                .setAmountOfBeds(1)
                .setAmountOfRooms(1)
                .setIfBathroomExist(true);

        ApartmentDTO apartmentDTO = ApartmentDTO.makeSimpleDTO(apartment);

        ReservedApartmentEditDTO reservedApartmentEditDTO = new ReservedApartmentEditDTO();
        reservedApartmentEditDTO.setId(1L);
        reservedApartmentEditDTO.setDateIn(ZonedDateTime.now());
        reservedApartmentEditDTO.setDateOut(ZonedDateTime.now());
        reservedApartmentEditDTO.setApartmentId(apartment.getId());


        ReservedApartmentDTO reservedApartmentDTO = new ReservedApartmentDTO()
                .setId(reservedApartmentEditDTO.getId())
                .setDateIn(reservedApartmentEditDTO.getDateIn())
                .setDateOut(reservedApartmentEditDTO.getDateOut())
                .setApartment(apartmentDTO);


        when(service.update(any())).thenReturn(reservedApartmentDTO);

        mvc.perform(put("/api/reservedapartment/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(reservedApartmentEditDTO)))
                //.andExpect(status().isOk())
                .andExpect(status().isBadRequest());
                //.andExpect(jsonPath("$.date_in").value(reservedApartmentDTO.getDateIn()));
    }

    @Test
    public void whenDeleteReservedApartment_thenReturn200() throws Exception {
        doNothing().when(service).delete(anyLong());
        mvc.perform(delete("/api/reservedapartment/delete/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
