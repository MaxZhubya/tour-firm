package com.summer.tourfirm.repository;

import com.summer.tourfirm.entity.ReservedApartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservedApartmentRepository extends JpaRepository<ReservedApartment, Long> {

    List<ReservedApartment> findByOrderByIdAsc();
    List<ReservedApartment> getByIdIn(List<Long> ids);
}
