package com.summer.tourfirm.service.impl;

import com.summer.tourfirm.dto.ApartmentDTO;
import com.summer.tourfirm.dto.edit.ApartmentEditDTO;
import com.summer.tourfirm.entity.Apartment;
import com.summer.tourfirm.entity.LiveBuilding;
import com.summer.tourfirm.exception.DataValidationException;
import com.summer.tourfirm.repository.ApartmentRepository;
import com.summer.tourfirm.service.IApartmentService;
import com.summer.tourfirm.service.ILiveBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApartmentServiceImpl implements IApartmentService {

    @Autowired
    private ILiveBuildingService liveBuildingService;

    @Autowired
    private ApartmentRepository repository;


    @Override
    @Transactional(readOnly = true)
    public ApartmentDTO get(Long id) {
        return ApartmentDTO.makeDTO(getEntity(id));
    }


    @Override
    @Transactional(readOnly = true)
    public List<ApartmentDTO> getAll() {
        return repository.findByOrderByIdAsc().stream()
                .map(ApartmentDTO::makeDTO).collect(Collectors.toList());
    }


    @Override
    public ApartmentDTO create(ApartmentEditDTO apartmentEditDTO) {
        Apartment apartment = new Apartment();

        apartment = repository.save(apartment);

        // Set data from ApartmentEditDTO
        setInputData(apartment, apartmentEditDTO);

        return ApartmentDTO.makeDTO(repository.save(apartment));
    }


    @Override
    public ApartmentDTO update(ApartmentEditDTO apartmentEditDTO) {
        if (Objects.nonNull(apartmentEditDTO.getId()))
            throw new DataValidationException("ID can not be null!");






        return null;
    }


    @Override
    public void delete(Long id) {

    }


    @Override
    public Apartment save(Apartment apartment) {
        return null;
    }


    @Override
    public Apartment getEntity(Long id) {
        return null;
    }


    @Override
    public List<Apartment> getEntitiesByIds(List<Long> ids) {
        return null;
    }


    private void setInputData(final Apartment apartment, ApartmentEditDTO apartmentEditDTO) {

        // Set Price
        apartment.setPrice(apartmentEditDTO.getPrice());

        // Set AmountOfBeds
        apartment.setAmountOfBeds(apartmentEditDTO.getAmountOfBeds());

        // Set AmountOfRooms
        apartment.setAmountOfRooms(apartmentEditDTO.getAmountOfRooms());

        // Set IfBathroomExist
        apartment.setIfBathroomExist(apartmentEditDTO.getIfBathroomExist());
    }

    private void clearRelatedData(Apartment apartment) {
        LiveBuilding building = apartment.getBuilding();
        if (Objects.nonNull(building)) {
            building.setApartments(null);
            liveBuildingService.save(building);
        }
    }

}
