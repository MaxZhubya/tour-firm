package com.summer.tourfirm.repository;

import com.summer.tourfirm.entity.ResortCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResortCityRepository extends JpaRepository<ResortCity, Long> {

    List<ResortCity> findByOrderByIdAsc();
    List<ResortCity> getByIdIn(List<Long> ids);
}
