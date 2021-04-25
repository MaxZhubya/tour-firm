package com.summer.tourfirm.services;

import com.summer.tourfirm.dto.ImageDTO;
import com.summer.tourfirm.dto.edit.ImageEditDTO;
import com.summer.tourfirm.entity.Image;
import com.summer.tourfirm.entity.LiveBuilding;
import com.summer.tourfirm.entity.enums.BuildingEnum;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.repository.ImageRepository;
import com.summer.tourfirm.service.impl.ImageServiceImpl;
import com.summer.tourfirm.service.impl.LiveBuildingServiceImpl;
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
public class ImageServiceTest {

    @InjectMocks
    private ImageServiceImpl imageService;

    @Mock
    private LiveBuildingServiceImpl liveBuildingService;

    @Mock
    private ImageRepository imageRepository;

    @Test
    public void whenGetImage_thenReturnImageList() {
        Image image = new Image()
                .setName("name");

        ImageDTO imageDTO = ImageDTO.makeSimpleDTO(image);
        when(imageRepository.findByOrderByIdAsc()).thenReturn(Arrays.asList(image));

        List<ImageDTO> imageDTOList = imageService.getAll();

        assertThat(imageDTOList).hasSize(1);
        assertThat(imageDTOList.get(0)).isEqualToComparingFieldByField(imageDTO);
    }

    @Test
    public void whenGetImageById_thenReturnOneImage() {
        Image image = new Image()
                .setId(1L)
                .setName("name");

        ImageDTO imageDTO = ImageDTO.makeSimpleDTO(image);
        when(imageRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(image));

        ImageDTO foundDTO = imageService.get(imageDTO.getId());

        assertThat(foundDTO).isEqualToComparingFieldByField(imageDTO);
    }

    @Test
    public void whenGetImageByIdIsNotExist_thenThrowDataNotFoundException() {
        when(imageRepository.findById(anyLong())).thenThrow(DataNotFoundException.class);
        Exception exception = assertThrows(DataNotFoundException.class, () -> {
            imageService.get(1L);
        });

    }

    @Test
    public void whenCreateImage_thenReturnImageDTO() {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setId(1L)
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        ImageEditDTO imageEditDTO = new ImageEditDTO();
        imageEditDTO.setName("number");
        imageEditDTO.setBuildingId(liveBuilding.getId());

        when(liveBuildingService.getEntity(anyLong())).thenReturn((liveBuilding));

        when(liveBuildingService.save((LiveBuilding) any())).thenReturn(liveBuilding);

        ImageDTO imageDTO = imageService.create(imageEditDTO);

        assertThat(imageDTO.getName()).isSameAs(imageEditDTO.getName());
    }

    @Test
    public void whenUpdateImage_thenReturnImageDTO() {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setId(1L)
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        ImageEditDTO imageEditDTO = new ImageEditDTO();
        imageEditDTO.setId(1L);
        imageEditDTO.setName("name");
        imageEditDTO.setBuildingId(liveBuilding.getId());

        Image image = new Image()
                .setId(imageEditDTO.getId())
                .setName(imageEditDTO.getName())
                .setBuilding(liveBuilding);

        when(imageRepository.findById(anyLong())).thenReturn(Optional.ofNullable(image));

        when(imageRepository.save(any())).thenReturn(image);

        ImageDTO imageDTO = imageService.update(imageEditDTO);

        assertThat(imageDTO.getName()).isSameAs(imageEditDTO.getName());
    }

    @Test
    public void whenDeleteImage_thenVoid() {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setId(1L)
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        Image image = new Image()
                .setId(1L)
                .setName("name")
                .setBuilding(liveBuilding);

        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(image));

        when(liveBuildingService.save((LiveBuilding) any())).thenReturn(liveBuilding);

        doNothing().when(imageRepository).delete(any());
        imageService.delete(image.getId());

        verify(imageRepository).delete(any());

    }
}
