package com.summer.tourfirm.repositories;

import com.summer.tourfirm.entity.ResortArea;
import com.summer.tourfirm.repository.ResortAreaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ResortAreaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ResortAreaRepository repository;

    @Test
    public void whenFindAll_thenReturnAllResortAreas() {
        ResortArea resortArea = new ResortArea()
                .setName("Temp Area For Tests");

        entityManager.persist(resortArea);
        entityManager.flush();

        List<ResortArea> foundResortAreaList = repository.findAll();

        assertThat(foundResortAreaList.get(0)).isEqualToComparingFieldByField(resortArea);
    }

    @Test
    public void whenFindAll_thenReturnEmptyList() {
        List<ResortArea> foundResortAreaList = repository.findAll();
        assertThat(foundResortAreaList).hasSize(0);
    }

    @Test
    public void whenFindById_thenReturnOneResortArea() {
        ResortArea resortArea = new ResortArea()
                .setName("Test Area Name");

        entityManager.persist(resortArea);
        entityManager.flush();

        Optional<ResortArea> foundArea = repository.findById(resortArea.getId());

        assertThat(foundArea.get().getName()).isEqualTo(resortArea.getName());
    }

    @Test
    public void whenSaveResortArea_thenReturnResortArea() {
        ResortArea resortCity = new ResortArea()
                .setName("Test Area Name");

        entityManager.persist(resortCity);
        entityManager.flush();

        ResortArea savedResortArea = repository.save(resortCity);

        assertThat(savedResortArea.getName()).isEqualTo(resortCity.getName());
    }

    @Test
    public void whenDeleteResortArea_thenVoid() {
        ResortArea resortArea = new ResortArea()
                .setName("Test Area Name");

        entityManager.persist(resortArea);
        entityManager.flush();

        repository.delete(resortArea);

        Optional<ResortArea> deletedResortArea = repository.findById(resortArea.getId());

        assertThat(deletedResortArea).isEmpty();
    }
}
