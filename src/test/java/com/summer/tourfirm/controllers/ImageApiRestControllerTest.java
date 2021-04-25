package com.summer.tourfirm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.summer.tourfirm.dto.LiveBuildingDTO;
import com.summer.tourfirm.dto.ImageDTO;
import com.summer.tourfirm.dto.edit.ImageEditDTO;
import com.summer.tourfirm.entity.Image;
import com.summer.tourfirm.entity.LiveBuilding;
import com.summer.tourfirm.entity.enums.BuildingEnum;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.service.IImageService;
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
public class ImageApiRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IImageService service;

    @Test
    public void whenGetImages_thenReturnImagesList() throws Exception {
        Image image = new Image()
                .setName("Name");

        ImageDTO imageDTO = ImageDTO.makeSimpleDTO(image);

        List<ImageDTO> imageDTOList = Arrays.asList(imageDTO);

        when(service.getAll()).thenReturn(imageDTOList);

        mvc.perform(get("/api/image/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(imageDTO.getName()));
    }

    @Test
    public void whenGetImageById_thenReturnOneImage() throws Exception {
        Image image = new Image()
                .setName("Name");

        ImageDTO imageDTO = ImageDTO.makeSimpleDTO(image);

        when(service.get(anyLong())).thenReturn(imageDTO);

        mvc.perform(get("/api/image/list/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(imageDTO.getName()));
    }

    @Test
    public void whenGetImageById_thenReturn404() throws Exception {

        when(service.get(anyLong())).thenThrow(DataNotFoundException.class);

        mvc.perform(get("/api/image/list/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenCreateImage_thenReturn200() throws Exception {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setId(1L)
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        ImageEditDTO imageEditDTO = new ImageEditDTO();
        imageEditDTO.setName("Name");
        imageEditDTO.setBuildingId(liveBuilding.getId());

        Image image = new Image()
                .setName(imageEditDTO.getName())
                .setBuilding(liveBuilding);

        ImageDTO imageDTO = ImageDTO.makeSimpleDTO(image);
        when(service.create(any())).thenReturn(imageDTO);

        mvc.perform(post("/api/image/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(imageEditDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(imageDTO.getName()));
    }

    @Test
    public void whenUpdateImage_thenReturn200() throws Exception {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setId(1L)
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        LiveBuildingDTO liveBuildingDTO = LiveBuildingDTO.makeSimpleDTO(liveBuilding);

        ImageEditDTO imageEditDTO = new ImageEditDTO();
        imageEditDTO.setId(1L);
        imageEditDTO.setName("Name");
        imageEditDTO.setBuildingId(liveBuilding.getId());


        ImageDTO imageDTO = new ImageDTO()
                .setId(imageEditDTO.getId())
                .setName(imageEditDTO.getName())
                .setBuilding(liveBuildingDTO);


        when(service.update(any())).thenReturn(imageDTO);

        mvc.perform(put("/api/image/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(imageEditDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(imageDTO.getName()));
    }

    @Test
    public void whenDeleteImage_thenReturn200() throws Exception {
        doNothing().when(service).delete(anyLong());
        mvc.perform(delete("/api/image/delete/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
