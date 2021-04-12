package com.summer.tourfirm.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.summer.tourfirm.dto.ResortCityDTO;
import com.summer.tourfirm.dto.ResortAreaDTO;
import com.summer.tourfirm.dto.edit.ResortAreaEditDTO;
import com.summer.tourfirm.entity.ResortCity;
import com.summer.tourfirm.entity.ResortArea;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.service.IResortAreaService;
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
public class ResortAreaApiRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IResortAreaService service;

    @Test
    public void whenGetResortAreas_thenReturnResortAreasList() throws Exception {
        ResortArea resortArea = new ResortArea()
                .setName("Temp ResortArea For Tests");

        ResortAreaDTO resortAreaDTO = ResortAreaDTO.makeSimpleDTO(resortArea);

        List<ResortAreaDTO> resortAreaDTOList = Arrays.asList(resortAreaDTO);

        when(service.getAll()).thenReturn(resortAreaDTOList);

        mvc.perform(get("/api/resortarea/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(resortAreaDTO.getName()));
    }

    @Test
    public void whenGetResortAreaById_thenReturnOneResortArea() throws Exception {
        ResortArea resortArea = new ResortArea()
                .setName("Temp ResortArea For Tests");

        ResortAreaDTO resortAreaDTO = ResortAreaDTO.makeSimpleDTO(resortArea);

        when(service.get(anyLong())).thenReturn(resortAreaDTO);

        mvc.perform(get("/api/resortarea/list/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(resortAreaDTO.getName()));
    }

    @Test
    public void whenGetResortAreaById_thenReturn404() throws Exception {

        when(service.get(anyLong())).thenThrow(DataNotFoundException.class);

        mvc.perform(get("/api/resortarea/list/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenCreateResortArea_thenReturn200() throws Exception {
        ResortCity resortCity = new ResortCity()
                .setId(1L)
                .setName("name")
                .setIsAbleForEntering(true);

        ResortAreaEditDTO resortAreaEditDTO = new ResortAreaEditDTO();
        resortAreaEditDTO.setName("Temp ResortArea For Tests");
        resortAreaEditDTO.setCityId(resortCity.getId());

        ResortArea resortArea = new ResortArea()
                .setName(resortAreaEditDTO.getName())
                .setCity(resortCity);

        ResortAreaDTO resortAreaDTO = ResortAreaDTO.makeSimpleDTO(resortArea);
        when(service.create(any())).thenReturn(resortAreaDTO);

        mvc.perform(post("/api/resortarea/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(resortAreaEditDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(resortAreaDTO.getName()));
    }

    @Test
    public void whenUpdateResortArea_thenReturn200() throws Exception {
        ResortCity resortCity = new ResortCity()
                .setId(1L)
                .setName("name")
                .setIsAbleForEntering(true);

        ResortCityDTO resortCityDTO = new ResortCityDTO()
                .setId(resortCity.getId())
                .setName(resortCity.getName())
                .setIsAbleForEntering(resortCity.getIsAbleForEntering());

        ResortAreaEditDTO resortAreaEditDTO = new ResortAreaEditDTO();
        resortAreaEditDTO.setId(1L);
        resortAreaEditDTO.setName("Temp ResortArea For Tests");
        resortAreaEditDTO.setCityId(resortCity.getId());


        ResortAreaDTO resortAreaDTO = new ResortAreaDTO()
                .setId(resortAreaEditDTO.getId())
                .setName(resortAreaEditDTO.getName())
                .setCity(resortCityDTO);

        when(service.update(any())).thenReturn(resortAreaDTO);

        mvc.perform(put("/api/resortarea/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(resortAreaEditDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(resortAreaDTO.getName()));
    }

    @Test
    public void whenDeleteResortArea_thenReturn200() throws Exception {
        doNothing().when(service).delete(anyLong());
        mvc.perform(delete("/api/resortarea/delete/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
