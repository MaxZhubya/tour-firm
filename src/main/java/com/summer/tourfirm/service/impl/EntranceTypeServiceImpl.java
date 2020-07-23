package com.summer.tourfirm.service.impl;

import com.summer.tourfirm.dto.EntranceTypeDTO;
import com.summer.tourfirm.dto.edit.EntranceTypeEditDTO;
import com.summer.tourfirm.entity.types.EntranceType;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.exception.DataValidationException;
import com.summer.tourfirm.repository.EntranceTypeRepository;
import com.summer.tourfirm.service.IEntranceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class EntranceTypeServiceImpl implements IEntranceTypeService {

    @Autowired
    private EntranceTypeRepository repository;

    @Override
    @Transactional(readOnly = true)
    public EntranceTypeDTO get(Long id) {
        return EntranceTypeDTO.makeDTO(getEntity(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntranceTypeDTO> getAll() {
        return repository.findByOrderByIdAsc().stream()
                .map(EntranceTypeDTO::makeDTO).collect(Collectors.toList());
    }

    @Override
    public EntranceTypeDTO create(EntranceTypeEditDTO typeEditDTO) {
        EntranceType type = new EntranceType()
                .setType(typeEditDTO.getType());

        type = repository.save(type);

        setInputData(type, typeEditDTO);

        return EntranceTypeDTO.makeDTO(repository.save(type));
    }

    @Override
    public EntranceTypeDTO update(EntranceTypeEditDTO typeEditDTO) {
        if (Objects.isNull(typeEditDTO.getId()))
            throw new DataValidationException("ID can't be null!");

        EntranceType type = getEntity(typeEditDTO.getId());

        clearRelatedData(type);

        setInputData(type, typeEditDTO);

        return EntranceTypeDTO.makeDTO(repository.save(type));
    }

    @Override
    public void delete(Long id) {
        EntranceType type = getEntity(id);

        clearRelatedData(type);

        repository.delete(type);
    }

    @Override
    public EntranceType save(EntranceType type) {
        return repository.save(type);
    }

    @Override
    public List<EntranceType> save(List<EntranceType> types) {
        return repository.saveAll(types);
    }

    @Override
    public EntranceType getEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("EntranceType with id: "
                + id.toString() + " is not existed"));
    }

    @Override
    public List<EntranceType> getEntitiesByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public void setInputData(final EntranceType type, EntranceTypeEditDTO typeEditDTO) {
        type.setType(typeEditDTO.getType());
    }

    public void clearRelatedData(EntranceType type) {
        // No related data
    }
}
