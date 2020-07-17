package com.summer.tourfirm.service;

import com.summer.tourfirm.dto.ApartmentDTO;
import com.summer.tourfirm.dto.edit.ApartmentEditDTO;
import com.summer.tourfirm.entity.Apartment;

import java.util.List;

public interface IApartmentService {

    ApartmentDTO get(Long id);      // Read
    List<ApartmentDTO> getAll();    // Read
    ApartmentDTO create(ApartmentEditDTO apartmentEditDTO);   // Create
    ApartmentDTO update(ApartmentEditDTO apartmentEditDTO);   // Update
    void delete(Long id);   // Delete
    Apartment save(Apartment apartment);
    List<Apartment> save(List<Apartment> apartments);
    Apartment getEntity(Long id);
    List<Apartment> getEntitiesByIds(List<Long> ids);
}
