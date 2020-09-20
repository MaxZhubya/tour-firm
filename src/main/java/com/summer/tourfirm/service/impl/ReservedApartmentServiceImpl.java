package com.summer.tourfirm.service.impl;

import com.summer.tourfirm.dto.ReservedApartmentDTO;
import com.summer.tourfirm.dto.edit.ReservedApartmentEditDTO;
import com.summer.tourfirm.entity.Apartment;
import com.summer.tourfirm.entity.ReservedApartment;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.exception.DataValidationException;
import com.summer.tourfirm.repository.ReservedApartmentRepository;
import com.summer.tourfirm.service.IApartmentService;
import com.summer.tourfirm.service.IReservedApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservedApartmentServiceImpl implements IReservedApartmentService {

    @Autowired
    private IApartmentService apartmentService;

    @Autowired
    private ReservedApartmentRepository repository;


    @Override
    @Transactional(readOnly = true)
    public ReservedApartmentDTO get(Long id) {
        return ReservedApartmentDTO.makeDTO(getEntity(id));
    }


    @Override
    @Transactional(readOnly = true)
    public List<ReservedApartmentDTO> getAll() {
        return repository.findByOrderByIdAsc().stream()
                .map(ReservedApartmentDTO::makeDTO).collect(Collectors.toList());
    }


    @Override
    public ReservedApartmentDTO create(ReservedApartmentEditDTO reservedApartmentEditDTO) {
        ReservedApartment reservedApartment = new ReservedApartment();
        setInputData(reservedApartment, reservedApartmentEditDTO);
        Apartment apartment = apartmentService.getEntity(reservedApartmentEditDTO.getApartmentId());
        reservedApartment.setApartment(apartment);
        apartment.getReservedApartments().add(reservedApartment);
        apartment = apartmentService.save(apartment);
        return ReservedApartmentDTO.makeDTO(apartment.getReservedApartments()
                .stream().sorted((value1, value2) -> Long.compare(value2.getId(), value1.getId()))
                .findFirst().get()
        );
    }


    @Override
    public ReservedApartmentDTO update(ReservedApartmentEditDTO reservedApartmentEditDTO) {
        if (Objects.isNull(reservedApartmentEditDTO.getId()))
            throw new DataValidationException("ID can't be null!");

        ReservedApartment reservedApartment = getEntity(reservedApartmentEditDTO.getId());
        setInputData(reservedApartment, reservedApartmentEditDTO);
        return ReservedApartmentDTO.makeDTO(repository.save(reservedApartment));
    }


    @Override
    public void delete(Long id) {
        ReservedApartment reservedApartment = getEntity(id);
        Apartment apartment = reservedApartment.getApartment();
        apartment.getReservedApartments().remove(reservedApartment);
        apartmentService.save(apartment);
        repository.delete(reservedApartment);
    }


    @Override
    public ReservedApartment save(ReservedApartment reservedApartment) {
        return repository.save(reservedApartment);
    }


    @Override
    public List<ReservedApartment> save(List<ReservedApartment> reservedApartments) {
        return repository.saveAll(reservedApartments);
    }


    @Override
    public ReservedApartment getEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("ReservedApartment with id: "
                + id.toString() + " is not existed"));
    }


    @Override
    public List<ReservedApartment> getEntitiesByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }


    private void setInputData(final ReservedApartment reservedApartment, ReservedApartmentEditDTO reservedApartmentEditDTO) {
        reservedApartment.setDateIn(reservedApartmentEditDTO.getDateIn())
                         .setDateOut(reservedApartmentEditDTO.getDateOut());
    }
}
