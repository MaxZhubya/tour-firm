package com.summer.tourfirm.repository;

import com.summer.tourfirm.entity.types.EntranceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntranceTypeRepository extends JpaRepository<EntranceType, Long> {

    List<EntranceType> findByOrderByIdAsc();
    List<EntranceType> getByIdIn(List<Long> ids);
    EntranceType findByType(String type);
}
