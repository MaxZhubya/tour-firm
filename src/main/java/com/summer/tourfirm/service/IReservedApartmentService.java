package com.summer.tourfirm.service;

import com.summer.tourfirm.dto.ReservedApartmentDTO;
import com.summer.tourfirm.dto.edit.ReservedApartmentEditDTO;
import com.summer.tourfirm.entity.ReservedApartment;

import java.util.List;

public interface IReservedApartmentService {

    ReservedApartmentDTO get(Long id);      // Read
    List<ReservedApartmentDTO> getAll();    // Read
    ReservedApartmentDTO create(ReservedApartmentEditDTO reservedApartmentEditDTO);   // Create
    ReservedApartmentDTO update(ReservedApartmentEditDTO reservedApartmentEditDTO);   // Update
    void delete(Long id);   // Delete
    ReservedApartment save(ReservedApartment reservedApartment);
    List<ReservedApartment> save(List<ReservedApartment> reservedApartments);
    ReservedApartment getEntity(Long id);
    List<ReservedApartment> getEntitiesByIds(List<Long> ids);
}
