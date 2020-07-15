package com.summer.tourfirm.repository;

import com.summer.tourfirm.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    List<Apartment> findByOrderByIdAsc();
    List<Apartment> getByIdIn(List<Long> ids);
}
