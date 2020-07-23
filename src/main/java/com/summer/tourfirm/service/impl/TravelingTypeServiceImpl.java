package com.summer.tourfirm.service.impl;

import com.summer.tourfirm.dto.TravelingTypeDTO;
import com.summer.tourfirm.dto.edit.TravelingTypeEditDTO;
import com.summer.tourfirm.entity.types.TravelingType;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.exception.DataValidationException;
import com.summer.tourfirm.repository.TravelingTypeRepository;
import com.summer.tourfirm.service.ITravelingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class TravelingTypeServiceImpl implements ITravelingTypeService {

    @Autowired
    private TravelingTypeRepository repository;

    @Override
    @Transactional(readOnly = true)
    public TravelingTypeDTO get(Long id) {
        return TravelingTypeDTO.makeDTO(getEntity(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TravelingTypeDTO> getAll() {
        return repository.findByOrderByIdAsc().stream()
                .map(TravelingTypeDTO::makeDTO).collect(Collectors.toList());
    }

    @Override
    public TravelingTypeDTO create(TravelingTypeEditDTO typeEditDTO) {
        TravelingType type = new TravelingType()
                .setType(typeEditDTO.getType());

        type = repository.save(type);

        setInputData(type, typeEditDTO);

        return TravelingTypeDTO.makeDTO(repository.save(type));
    }

    @Override
    public TravelingTypeDTO update(TravelingTypeEditDTO typeEditDTO) {
        if (Objects.isNull(typeEditDTO.getId()))
            throw new DataValidationException("ID can't be null!");

        TravelingType type = getEntity(typeEditDTO.getId());

        clearRelatedData(type);

        setInputData(type, typeEditDTO);

        return TravelingTypeDTO.makeDTO(repository.save(type));
    }

    @Override
    public void delete(Long id) {
        TravelingType type = getEntity(id);

        clearRelatedData(type);

        repository.delete(type);
    }

    @Override
    public TravelingType save(TravelingType type) {
        return repository.save(type);
    }

    @Override
    public List<TravelingType> save(List<TravelingType> types) {
        return repository.saveAll(types);
    }

    @Override
    public TravelingType getEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("TravelingType with id: "
                + id.toString() + " is not existed"));
    }

    @Override
    public List<TravelingType> getEntitiesByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public void setInputData(final TravelingType type, TravelingTypeEditDTO typeEditDTO) {
        type.setType(typeEditDTO.getType());
    }

    public void clearRelatedData(TravelingType type) {
        // No related data
    }
}
