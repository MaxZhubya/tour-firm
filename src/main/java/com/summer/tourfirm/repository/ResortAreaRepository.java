package com.summer.tourfirm.repository;

import com.summer.tourfirm.entity.ResortArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResortAreaRepository extends JpaRepository<ResortArea, Long> {

    List<ResortArea> findByOrderByIdAsc();
    List<ResortArea> getByIdIn(List<Long> ids);
}
