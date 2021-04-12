package com.summer.tourfirm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.summer.tourfirm.dto.CountryDTO;
import com.summer.tourfirm.dto.edit.CountryEditDTO;
import com.summer.tourfirm.entity.Country;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.service.ICountryService;
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
public class CountryApiRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ICountryService service;

    @Test
    public void whenGetCountries_thenReturnCountryList() throws Exception {
        Country country = new Country()
                .setAbleForEntering(false)
                .setName("Temp Country For Tests");

        CountryDTO countryDTO = CountryDTO.makeSimpleDTO(country);

        List<CountryDTO> countryDTOList = Arrays.asList(countryDTO);

        when(service.getAll()).thenReturn(countryDTOList);

        mvc.perform(get("/api/country/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(countryDTO.getName()));
    }

    @Test
    public void whenGetCountryById_thenReturnOneCountry() throws Exception {
        Country country = new Country()
                .setAbleForEntering(false)
                .setName("Temp Country For Tests");

        CountryDTO countryDTO = CountryDTO.makeSimpleDTO(country);

        when(service.get(anyLong())).thenReturn(countryDTO);

        mvc.perform(get("/api/country/list/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(countryDTO.getName()));
    }

    @Test
    public void whenGetCountryById_thenReturn404() throws Exception {

        when(service.get(anyLong())).thenThrow(DataNotFoundException.class);

        mvc.perform(get("/api/country/list/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenCreateCountry_thenReturn200() throws Exception {
        CountryEditDTO countryEditDTO = new CountryEditDTO();
        countryEditDTO.setIsAbleForEntering(true);
        countryEditDTO.setName("Temp Country For Tests");

        Country country = new Country()
                .setAbleForEntering(countryEditDTO.getIsAbleForEntering())
                .setName(countryEditDTO.getName());

        CountryDTO countryDTO = CountryDTO.makeSimpleDTO(country);
        when(service.create(any())).thenReturn(countryDTO);

        mvc.perform(post("/api/country/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(countryEditDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(countryDTO.getName()));
    }

    @Test
    public void whenUpdateCountry_thenReturn200() throws Exception {
        CountryEditDTO countryEditDTO = new CountryEditDTO();
        countryEditDTO.setId(1L);
        countryEditDTO.setIsAbleForEntering(true);
        countryEditDTO.setName("Temp Country For Tests");


        CountryDTO countryDTO = new CountryDTO()
                .setId(countryEditDTO.getId())
                .setIsAbleForEntering(countryEditDTO.getIsAbleForEntering())
                .setName(countryEditDTO.getName());

        when(service.update(any())).thenReturn(countryDTO);

        mvc.perform(put("/api/country/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(countryEditDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(countryDTO.getName()));
    }

    @Test
    public void whenDeleteCountry_thenReturn200() throws Exception {
        doNothing().when(service).delete(anyLong());
        mvc.perform(delete("/api/country/delete/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /*===  EntranceType Rest  ===*/



}
