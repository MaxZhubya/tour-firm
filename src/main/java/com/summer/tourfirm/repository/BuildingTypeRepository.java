package com.summer.tourfirm.repository;

import com.summer.tourfirm.entity.types.BuildingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingTypeRepository extends JpaRepository<BuildingType, Long> {

    List<BuildingType> findByOrderByIdAsc();
    List<BuildingType> getByIdIn(List<Long> ids);
}
