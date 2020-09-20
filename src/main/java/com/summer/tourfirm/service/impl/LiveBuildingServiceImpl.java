package com.summer.tourfirm.service.impl;

import com.summer.tourfirm.dto.ApartmentDTO;
import com.summer.tourfirm.dto.FilterDTO;
import com.summer.tourfirm.dto.LiveBuildingDTO;
import com.summer.tourfirm.dto.edit.LiveBuildingEditDTO;
import com.summer.tourfirm.entity.LiveBuilding;
import com.summer.tourfirm.entity.ResortArea;
import com.summer.tourfirm.exception.DataNotFoundException;
import com.summer.tourfirm.exception.DataValidationException;
import com.summer.tourfirm.repository.LiveBuildingRepository;
import com.summer.tourfirm.service.ILiveBuildingService;
import com.summer.tourfirm.service.IResortAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class LiveBuildingServiceImpl implements ILiveBuildingService {

    @Autowired
    private IResortAreaService areaService;

    @Autowired
    private LiveBuildingRepository repository;



    @Override
    @Transactional(readOnly = true)
    public LiveBuildingDTO get(Long id) {
        return LiveBuildingDTO.makeDTO(getEntity(id));
    }


    @Override
    @Transactional(readOnly = true)
    public List<LiveBuildingDTO> getAll() {
        return repository.findByOrderByIdAsc().stream().map(LiveBuildingDTO::makeDTO).collect(Collectors.toList());
    }


    private static Predicate<ApartmentDTO> isApartmentFree(ZonedDateTime dateIn, ZonedDateTime dateOut) {
        return p -> p.getReservedApartments().stream().noneMatch(value ->
                ((dateIn.isEqual(value.getDateIn()) || (dateIn.isAfter(value.getDateIn()) && dateIn.isBefore(value.getDateOut())))
                    || (dateOut.isAfter(value.getDateIn()) && (dateOut.isBefore(value.getDateOut()) || dateOut.isEqual(value.getDateOut()))))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<LiveBuildingDTO> getAllByFilter(FilterDTO filterDTO) {
        List<LiveBuildingDTO> liveBuildingDTOList = repository.findAllByFilter(filterDTO.getCountry(), filterDTO.getCity())
                .stream().map(LiveBuildingDTO::makeDTO).collect(Collectors.toList());

        for (LiveBuildingDTO liveBuildingDTO: liveBuildingDTOList) {
            List<ApartmentDTO> apartmentDTOList = liveBuildingDTO.getApartments()
                    .stream()
                    .filter(isApartmentFree(filterDTO.getDataIn(), filterDTO.getDataOut()))
                    .collect(Collectors.toList());
            liveBuildingDTO.setApartments(apartmentDTOList);
        }

        return liveBuildingDTOList
                .stream()
                .filter(value -> value.getApartments().size() > 0)
                .collect(Collectors.toList());
    }


    @Override
    public LiveBuildingDTO create(LiveBuildingEditDTO liveBuildingEditDTO) {
        LiveBuilding liveBuilding = new LiveBuilding();
        setInputData(liveBuilding, liveBuildingEditDTO);
        ResortArea area = areaService.getEntity(liveBuildingEditDTO.getAreaId());
        liveBuilding.setArea(area);
        area.getBuildings().add(liveBuilding);
        area = areaService.save(area);
        return LiveBuildingDTO.makeDTO(area.getBuildingByNumber(liveBuilding.getNumber()));
    }


    @Override
    public LiveBuildingDTO update(LiveBuildingEditDTO liveBuildingEditDTO) {
        if (Objects.isNull(liveBuildingEditDTO.getId()))
            throw new DataValidationException("ID can't be null!");

        LiveBuilding liveBuilding = getEntity(liveBuildingEditDTO.getId());
        setInputData(liveBuilding, liveBuildingEditDTO);
        return LiveBuildingDTO.makeDTO(repository.save(liveBuilding));
    }


    @Override
    public void delete(Long id) {
        LiveBuilding liveBuilding = getEntity(id);
        ResortArea area = liveBuilding.getArea();
        area.getBuildings().remove(liveBuilding);
        areaService.save(area);
        repository.delete(liveBuilding);
    }


    @Override
    public LiveBuilding save(LiveBuilding liveBuilding) {
        return repository.save(liveBuilding);
    }


    @Override
    public List<LiveBuilding> save(List<LiveBuilding> buildings) {
        return repository.saveAll(buildings);
    }


    @Override
    public LiveBuilding getEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("LiveBuilding with id: "
                + id.toString() + " is not existed"));
    }


    @Override
    public List<LiveBuilding> getEntitiesByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }


    private void setInputData(final LiveBuilding building, LiveBuildingEditDTO buildingEditDTO) {
        building.setType(buildingEditDTO.getType())
            .setNumber(buildingEditDTO.getNumber())
            .setAddress(buildingEditDTO.getAddress())
            .setName(buildingEditDTO.getName())
            .setIfPoolExist(buildingEditDTO.getIfPoolExist())
            .setIfParkingExist(buildingEditDTO.getIfParkingExist())
            .setDistanceToBeach(buildingEditDTO.getDistanceToBeach());

        //  building.setAvailableApartmentCount(buildingEditDTO.getApartmentIds.size());
    }

}
