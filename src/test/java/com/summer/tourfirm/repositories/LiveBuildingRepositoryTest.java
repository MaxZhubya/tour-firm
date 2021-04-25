package com.summer.tourfirm.repositories;

import com.summer.tourfirm.entity.LiveBuilding;
import com.summer.tourfirm.entity.enums.BuildingEnum;
import com.summer.tourfirm.repository.LiveBuildingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class LiveBuildingRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LiveBuildingRepository repository;

    @Test
    public void whenFindAll_thenReturnAllLiveBuildings() {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        entityManager.persist(liveBuilding);
        entityManager.flush();

        List<LiveBuilding> foundLiveBuildingList = repository.findAll();

        assertThat(foundLiveBuildingList.get(0)).isEqualToComparingFieldByField(liveBuilding);
    }

    @Test
    public void whenFindAll_thenReturnEmptyList() {
        List<LiveBuilding> foundLiveBuildingList = repository.findAll();
        assertThat(foundLiveBuildingList).hasSize(0);
    }

    @Test
    public void whenFindById_thenReturnOneLiveBuilding() {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        entityManager.persist(liveBuilding);
        entityManager.flush();

        Optional<LiveBuilding> foundLiveBuilding = repository.findById(liveBuilding.getId());

        assertThat(foundLiveBuilding.get().getName()).isEqualTo(liveBuilding.getName());
    }

    @Test
    public void whenSaveLiveBuilding_thenReturnLiveBuilding() {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        entityManager.persist(liveBuilding);
        entityManager.flush();

        LiveBuilding savedLiveBuilding = repository.save(liveBuilding);

        assertThat(savedLiveBuilding.getName()).isEqualTo(liveBuilding.getName());
    }

    @Test
    public void whenDeleteLiveBuilding_thenVoid() {
        LiveBuilding liveBuilding = new LiveBuilding()
                .setType(BuildingEnum.HOTEL)
                .setNumber("Number")
                .setAddress("Address")
                .setName("Name");

        entityManager.persist(liveBuilding);
        entityManager.flush();

        repository.delete(liveBuilding);

        Optional<LiveBuilding> deletedLiveBuilding = repository.findById(liveBuilding.getId());

        assertThat(deletedLiveBuilding).isEmpty();
    }
}
