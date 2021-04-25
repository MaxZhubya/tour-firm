package com.summer.tourfirm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.summer.tourfirm.dto.LiveBuildingDTO;
import com.summer.tourfirm.dto.ResortAreaDTO;
import com.summer.tourfirm.dto.edit.LiveBuildingEditDTO;
import com.summer.tourfirm.entity.LiveBuilding;
import com.summer.tourfirm.entity.ResortArea;
import com.summer.tourfirm.entity.enums.BuildingEnum;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.service.ILiveBuildingService;
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
public class LiveBuildingAliRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ILiveBuildingService service;

    @Test
    public void whenGetLiveBuildings_thenReturnLiveBuildingsList() throws Exception {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        LiveBuildingDTO liveBuildingDTO = LiveBuildingDTO.makeSimpleDTO(liveBuilding);

        List<LiveBuildingDTO> liveBuildingDTOList = Arrays.asList(liveBuildingDTO);

        when(service.getAll()).thenReturn(liveBuildingDTOList);

        mvc.perform(get("/api/livebuilding/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(liveBuildingDTO.getName()));
    }

    @Test
    public void whenGetLiveBuildingById_thenReturnOneLiveBuilding() throws Exception {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        LiveBuildingDTO liveBuildingDTO = LiveBuildingDTO.makeSimpleDTO(liveBuilding);

        when(service.get(anyLong())).thenReturn(liveBuildingDTO);

        mvc.perform(get("/api/livebuilding/list/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(liveBuildingDTO.getName()));
    }

    @Test
    public void whenGetLiveBuildingById_thenReturn404() throws Exception {

        when(service.get(anyLong())).thenThrow(DataNotFoundException.class);

        mvc.perform(get("/api/livebuilding/list/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenCreateLiveBuilding_thenReturn200() throws Exception {
        ResortArea resortArea = new ResortArea()
                .setId(1L)
                .setName("name");

        LiveBuildingEditDTO liveBuildingEditDTO = new LiveBuildingEditDTO();
        liveBuildingEditDTO.setType(BuildingEnum.HOTEL);
        liveBuildingEditDTO.setNumber("Number");
        liveBuildingEditDTO.setAddress("Address");
        liveBuildingEditDTO.setName("Name");
        liveBuildingEditDTO.setAreaId(resortArea.getId());

        LiveBuilding liveBuilding = new LiveBuilding()
                .setNumber(liveBuildingEditDTO.getNumber())
                .setAddress(liveBuildingEditDTO.getAddress())
                .setName(liveBuildingEditDTO.getName())
                .setArea(resortArea);

        LiveBuildingDTO liveBuildingDTO = LiveBuildingDTO.makeSimpleDTO(liveBuilding);
        when(service.create(any())).thenReturn(liveBuildingDTO);

        mvc.perform(post("/api/livebuilding/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(liveBuildingEditDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(liveBuildingDTO.getName()));
    }

    @Test
    public void whenUpdateLiveBuilding_thenReturn200() throws Exception {
        ResortArea resortArea = new ResortArea()
                .setId(1L)
                .setName("name");

        ResortAreaDTO resortAreaDTO = new ResortAreaDTO()
                .setId(resortArea.getId())
                .setName(resortArea.getName());

        LiveBuildingEditDTO liveBuildingEditDTO = new LiveBuildingEditDTO();
        liveBuildingEditDTO.setId(1L);
        liveBuildingEditDTO.setType(BuildingEnum.HOTEL);
        liveBuildingEditDTO.setNumber("Number");
        liveBuildingEditDTO.setAddress("Address");
        liveBuildingEditDTO.setName("Name");
        liveBuildingEditDTO.setAreaId(resortArea.getId());


        LiveBuildingDTO liveBuildingDTO = new LiveBuildingDTO()
                .setId(liveBuildingEditDTO.getId())
                .setType(liveBuildingEditDTO.getType())
                .setNumber(liveBuildingEditDTO.getNumber())
                .setAddress(liveBuildingEditDTO.getAddress())
                .setName(liveBuildingEditDTO.getName())
                .setArea(resortAreaDTO);


        when(service.update(any())).thenReturn(liveBuildingDTO);

        mvc.perform(put("/api/livebuilding/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(liveBuildingEditDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(liveBuildingDTO.getName()));
    }

    @Test
    public void whenDeleteLiveBuilding_thenReturn200() throws Exception {
        doNothing().when(service).delete(anyLong());
        mvc.perform(delete("/api/livebuilding/delete/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
