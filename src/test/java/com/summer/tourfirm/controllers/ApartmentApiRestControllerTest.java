package com.summer.tourfirm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.summer.tourfirm.dto.LiveBuildingDTO;
import com.summer.tourfirm.dto.ApartmentDTO;
import com.summer.tourfirm.dto.edit.ApartmentEditDTO;
import com.summer.tourfirm.entity.Apartment;
import com.summer.tourfirm.entity.LiveBuilding;
import com.summer.tourfirm.entity.enums.BuildingEnum;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.service.IApartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
public class ApartmentApiRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IApartmentService service;

    @Test
    public void whenGetApartments_thenReturnApartmentsList() throws Exception {
        Apartment apartment = new Apartment()
                .setNumber("Number")
                .setPrice(123)
                .setAmountOfBeds(1)
                .setAmountOfRooms(1)
                .setIfBathroomExist(true);

        ApartmentDTO apartmentDTO = ApartmentDTO.makeSimpleDTO(apartment);

        List<ApartmentDTO> apartmentDTOList = Arrays.asList(apartmentDTO);

        when(service.getAll()).thenReturn(apartmentDTOList);

        mvc.perform(get("/api/apartment/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].number").value(apartmentDTO.getNumber()));
    }

    @Test
    public void whenGetApartmentById_thenReturnOneApartment() throws Exception {
        Apartment apartment = new Apartment()
                .setNumber("number")
                .setPrice(123)
                .setAmountOfBeds(1)
                .setAmountOfRooms(1)
                .setIfBathroomExist(true);

        ApartmentDTO apartmentDTO = ApartmentDTO.makeSimpleDTO(apartment);

        when(service.get(anyLong())).thenReturn(apartmentDTO);

        mvc.perform(get("/api/apartment/list/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(apartmentDTO.getNumber()));
    }

    @Test
    public void whenGetApartmentById_thenReturn404() throws Exception {

        when(service.get(anyLong())).thenThrow(DataNotFoundException.class);

        mvc.perform(get("/api/apartment/list/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenCreateApartment_thenReturn200() throws Exception {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setId(1L)
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        ApartmentEditDTO apartmentEditDTO = new ApartmentEditDTO();
        apartmentEditDTO.setNumber("Number");
        apartmentEditDTO.setPrice(123);
        apartmentEditDTO.setAmountOfBeds(1);
        apartmentEditDTO.setAmountOfRooms(1);
        apartmentEditDTO.setIfBathroomExist(true);
        apartmentEditDTO.setBuildingId(liveBuilding.getId());

        Apartment apartment = new Apartment()
                .setNumber(apartmentEditDTO.getNumber())
                .setPrice(apartmentEditDTO.getPrice())
                .setAmountOfBeds(apartmentEditDTO.getAmountOfBeds())
                .setAmountOfRooms(apartmentEditDTO.getAmountOfRooms())
                .setIfBathroomExist(apartmentEditDTO.getIfBathroomExist())
                .setBuilding(liveBuilding);

        ApartmentDTO apartmentDTO = ApartmentDTO.makeSimpleDTO(apartment);
        when(service.create(any())).thenReturn(apartmentDTO);

        mvc.perform(post("/api/apartment/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(apartmentEditDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(apartmentDTO.getNumber()));
    }

    @Test
    public void whenUpdateApartment_thenReturn200() throws Exception {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setId(1L)
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        LiveBuildingDTO liveBuildingDTO = LiveBuildingDTO.makeSimpleDTO(liveBuilding);

        ApartmentEditDTO apartmentEditDTO = new ApartmentEditDTO();
        apartmentEditDTO.setId(1L);
        apartmentEditDTO.setNumber("Number");
        apartmentEditDTO.setPrice(123);
        apartmentEditDTO.setAmountOfBeds(1);
        apartmentEditDTO.setAmountOfRooms(1);
        apartmentEditDTO.setIfBathroomExist(true);
        apartmentEditDTO.setBuildingId(liveBuilding.getId());


        ApartmentDTO apartmentDTO = new ApartmentDTO()
                .setId(apartmentEditDTO.getId())
                .setNumber(apartmentEditDTO.getNumber())
                .setPrice(apartmentEditDTO.getPrice())
                .setAmountOfBeds(apartmentEditDTO.getAmountOfBeds())
                .setAmountOfRooms(apartmentEditDTO.getAmountOfRooms())
                .setIfBathroomExist(apartmentEditDTO.getIfBathroomExist())
                .setBuilding(liveBuildingDTO);


        when(service.update(any())).thenReturn(apartmentDTO);

        mvc.perform(put("/api/apartment/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(apartmentEditDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(apartmentDTO.getNumber()));
    }

    @Test
    public void whenDeleteApartment_thenReturn200() throws Exception {
        doNothing().when(service).delete(anyLong());
        mvc.perform(delete("/api/apartment/delete/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
