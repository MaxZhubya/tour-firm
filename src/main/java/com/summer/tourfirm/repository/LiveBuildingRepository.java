package com.summer.tourfirm.repository;

import com.summer.tourfirm.entity.LiveBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiveBuildingRepository extends JpaRepository<LiveBuilding, Long> {

    List<LiveBuilding> findByOrderByIdAsc();
    List<LiveBuilding> getByIdIn(List<Long> ids);
}
