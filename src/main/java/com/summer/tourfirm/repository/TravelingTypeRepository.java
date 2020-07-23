package com.summer.tourfirm.repository;

import com.summer.tourfirm.entity.types.TravelingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelingTypeRepository extends JpaRepository<TravelingType, Long> {

    List<TravelingType> findByOrderByIdAsc();
    List<TravelingType> getByIdIn(List<Long> ids);
}
