package com.summer.tourfirm.repository;

import com.summer.tourfirm.entity.LiveBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiveBuildingRepository extends JpaRepository<LiveBuilding, Long> {

    List<LiveBuilding> findByOrderByIdAsc();
    List<LiveBuilding> getByIdIn(List<Long> ids);

    @Query("select distinct bd from Country country join country.cities city join city.areas area join area.buildings bd join fetch bd.apartments where country.name = :country and city.name = :city")
    List<LiveBuilding> findAllByFilter(String country, String city);

}
