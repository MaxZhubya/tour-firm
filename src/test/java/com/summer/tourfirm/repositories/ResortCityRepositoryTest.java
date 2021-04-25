package com.summer.tourfirm.repositories;

import com.summer.tourfirm.entity.ResortCity;
import com.summer.tourfirm.repository.ResortCityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ResortCityRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ResortCityRepository repository;

    @Test
    public void whenFindAll_thenReturnAllResortCities() {
        ResortCity resortCity = new ResortCity()
                .setIsAbleForEntering(false)
                .setName("Temp City For Tests");

        entityManager.persist(resortCity);
        entityManager.flush();

        List<ResortCity> foundResortCityList = repository.findAll();

        assertThat(foundResortCityList.get(0)).isEqualToComparingFieldByField(resortCity);
    }

    @Test
    public void whenFindAll_thenReturnEmptyList() {
        List<ResortCity> foundResortCityList = repository.findAll();
        assertThat(foundResortCityList).hasSize(0);
    }

    @Test
    public void whenFindById_thenReturnOneResortCity() {
        ResortCity resortCity = new ResortCity()
                .setName("Test City Name")
                .setIsAbleForEntering(true);

        entityManager.persist(resortCity);
        entityManager.flush();

        Optional<ResortCity> foundCity = repository.findById(resortCity.getId());

        assertThat(foundCity.get().getName()).isEqualTo(resortCity.getName());
    }

    @Test
    public void whenSaveResortCity_thenReturnResortCity() {
        ResortCity resortCity = new ResortCity()
                .setName("Test City Name")
                .setIsAbleForEntering(true);

        entityManager.persist(resortCity);
        entityManager.flush();

        ResortCity savedResortCity = repository.save(resortCity);

        assertThat(savedResortCity.getName()).isEqualTo(resortCity.getName());
    }

    @Test
    public void whenDeleteResortCity_thenVoid() {
        ResortCity resortCity = new ResortCity()
                .setName("Test City Name")
                .setIsAbleForEntering(true);

        entityManager.persist(resortCity);
        entityManager.flush();

        repository.delete(resortCity);

        Optional<ResortCity> deletedResortCity = repository.findById(resortCity.getId());

        assertThat(deletedResortCity).isEmpty();
    }
}
