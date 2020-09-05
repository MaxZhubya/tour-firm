package com.summer.tourfirm.service.impl;

import com.summer.tourfirm.dto.ImageDTO;
import com.summer.tourfirm.dto.edit.ImageEditDTO;
import com.summer.tourfirm.entity.Image;
import com.summer.tourfirm.entity.LiveBuilding;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.exception.DataValidationException;
import com.summer.tourfirm.repository.ImageRepository;
import com.summer.tourfirm.service.IImageService;
import com.summer.tourfirm.service.ILiveBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ImageServiceImpl implements IImageService {

    @Autowired
    private ILiveBuildingService liveBuildingService;

    @Autowired
    private ImageRepository repository;


    @Override
    @Transactional(readOnly = true)
    public ImageDTO get(Long id) {
        return ImageDTO.makeDTO(getEntity(id));
    }


    @Override
    @Transactional(readOnly = true)
    public List<ImageDTO> getAll() {
        return repository.findByOrderByIdAsc().stream()
                .map(ImageDTO::makeDTO).collect(Collectors.toList());
    }


    @Override
    public ImageDTO create(ImageEditDTO imageEditDTO) {
        Image image = new Image();
        setInputData(image, imageEditDTO);
        LiveBuilding liveBuilding = liveBuildingService.getEntity(imageEditDTO.getBuildingId());
        image.setBuilding(liveBuilding);
        liveBuilding.getImages().add(image);
        liveBuilding = liveBuildingService.save(liveBuilding);

        return ImageDTO.makeDTO(liveBuilding.getImageByName(image.getName()));
    }


    @Override
    public ImageDTO update(ImageEditDTO imageEditDTO) {
        if (Objects.isNull(imageEditDTO.getId()))
            throw new DataValidationException("ID can't be null!");

        Image image = getEntity(imageEditDTO.getId());
        setInputData(image, imageEditDTO);
        return ImageDTO.makeDTO(repository.save(image));
    }


    @Override
    public void delete(Long id) {
        Image image = getEntity(id);
        LiveBuilding liveBuilding = image.getBuilding();
        liveBuilding.getImages().remove(image);
        liveBuildingService.save(liveBuilding);
        repository.delete(image);
    }


    @Override
    public Image save(Image image) {
        return repository.save(image);
    }


    @Override
    public List<Image> save(List<Image> images) {
        return repository.saveAll(images);
    }


    @Override
    public Image getEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Image with id: "
                + id.toString() + " is not existed"));
    }


    @Override
    public List<Image> getEntitiesByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }


    private void setInputData(final Image image, ImageEditDTO imageEditDTO) {
        image.setName(imageEditDTO.getName());
    }
}
