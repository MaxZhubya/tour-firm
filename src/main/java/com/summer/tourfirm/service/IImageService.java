package com.summer.tourfirm.service;

import com.summer.tourfirm.dto.ImageDTO;
import com.summer.tourfirm.dto.edit.ImageEditDTO;
import com.summer.tourfirm.entity.Image;

import java.util.List;

public interface IImageService {

    ImageDTO get(Long id);      // Read
    List<ImageDTO> getAll();    // Read
    ImageDTO create(ImageEditDTO imageEditDTO);   // Create
    ImageDTO update(ImageEditDTO imageEditDTO);   // Update
    void delete(Long id);   // Delete
    Image save(Image image);
    List<Image> save(List<Image> images);
    Image getEntity(Long id);
    List<Image> getEntitiesByIds(List<Long> ids);
}
