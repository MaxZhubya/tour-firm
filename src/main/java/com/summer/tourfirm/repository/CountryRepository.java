package com.summer.tourfirm.repository;

import com.summer.tourfirm.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findByOrderByIdAsc();
    List<Country> getByIdIn(List<Long> ids);
}
