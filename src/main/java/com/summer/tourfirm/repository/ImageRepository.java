package com.summer.tourfirm.repository;

import com.summer.tourfirm.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByOrderByIdAsc();
    List<Image> getByIdIn(List<Long> ids);
}
