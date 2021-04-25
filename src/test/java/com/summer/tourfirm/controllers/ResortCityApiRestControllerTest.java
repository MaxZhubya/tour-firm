package com.summer.tourfirm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.summer.tourfirm.dto.CountryDTO;
import com.summer.tourfirm.dto.ResortCityDTO;
import com.summer.tourfirm.dto.edit.ResortCityEditDTO;
import com.summer.tourfirm.entity.Country;
import com.summer.tourfirm.entity.ResortCity;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.service.IResortCityService;
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
public class ResortCityApiRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IResortCityService service;

    @Test
    public void whenGetResortCities_thenReturnResortCityList() throws Exception {
        ResortCity resortCity = new ResortCity()
                .setIsAbleForEntering(true)
                .setName("Temp ResortCity For Tests");

        ResortCityDTO resortCityDTO = ResortCityDTO.makeSimpleDTO(resortCity);

        List<ResortCityDTO> resortCityDTOList = Arrays.asList(resortCityDTO);

        when(service.getAll()).thenReturn(resortCityDTOList);

        mvc.perform(get("/api/resortcity/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(resortCityDTO.getName()));
    }

    @Test
    public void whenGetResortCityById_thenReturnOneResortCity() throws Exception {
        ResortCity resortCity = new ResortCity()
                .setIsAbleForEntering(true)
                .setName("Temp ResortCity For Tests");

        ResortCityDTO resortCityDTO = ResortCityDTO.makeSimpleDTO(resortCity);

        when(service.get(anyLong())).thenReturn(resortCityDTO);

        mvc.perform(get("/api/resortcity/list/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(resortCityDTO.getName()));
    }

    @Test
    public void whenGetResortCityById_thenReturn404() throws Exception {

        when(service.get(anyLong())).thenThrow(DataNotFoundException.class);

        mvc.perform(get("/api/resortcity/list/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenCreateResortCity_thenReturn200() throws Exception {
        Country country = new Country()
                .setId(1L)
                .setName("name")
                .setAbleForEntering(true);

        ResortCityEditDTO resortCityEditDTO = new ResortCityEditDTO();
        resortCityEditDTO.setIsAbleForEntering(true);
        resortCityEditDTO.setName("Temp ResortCity For Tests");
        resortCityEditDTO.setCountryId(country.getId());

        ResortCity resortCity = new ResortCity()
                .setIsAbleForEntering(resortCityEditDTO.getIsAbleForEntering())
                .setName(resortCityEditDTO.getName())
                .setCountry(country);

        ResortCityDTO resortCityDTO = ResortCityDTO.makeSimpleDTO(resortCity);
        when(service.create(any())).thenReturn(resortCityDTO);

        mvc.perform(post("/api/resortcity/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(resortCityEditDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(resortCityDTO.getName()));
    }

    @Test
    public void whenUpdateResortCity_thenReturn200() throws Exception {
        Country country = new Country()
                .setId(1L)
                .setName("name")
                .setAbleForEntering(true);

        CountryDTO countryDTO = CountryDTO.makeSimpleDTO(country);

        ResortCityEditDTO resortCityEditDTO = new ResortCityEditDTO();
        resortCityEditDTO.setId(1L);
        resortCityEditDTO.setIsAbleForEntering(true);
        resortCityEditDTO.setName("Temp ResortCity For Tests");
        resortCityEditDTO.setCountryId(country.getId());


        ResortCityDTO resortCityDTO = new ResortCityDTO()
                .setId(resortCityEditDTO.getId())
                .setIsAbleForEntering(resortCityEditDTO.getIsAbleForEntering())
                .setName(resortCityEditDTO.getName())
                .setCountry(countryDTO);

        when(service.update(any())).thenReturn(resortCityDTO);

        mvc.perform(put("/api/resortcity/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(resortCityEditDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(resortCityDTO.getName()));
    }

    @Test
    public void whenDeleteResortCity_thenReturn200() throws Exception {
        doNothing().when(service).delete(anyLong());
        mvc.perform(delete("/api/resortcity/delete/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
