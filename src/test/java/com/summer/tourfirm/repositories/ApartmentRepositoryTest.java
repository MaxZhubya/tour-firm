package com.summer.tourfirm.repositories;

import com.summer.tourfirm.entity.Apartment;
import com.summer.tourfirm.repository.ApartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ApartmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ApartmentRepository repository;

    @Test
    public void whenFindAll_thenReturnAllApartments() {
        Apartment apartment = new Apartment()
                .setNumber("number")
                .setPrice(123)
                .setAmountOfBeds(1)
                .setAmountOfRooms(1)
                .setIfBathroomExist(true);

        entityManager.persist(apartment);
        entityManager.flush();

        List<Apartment> foundApartmentList = repository.findAll();

        assertThat(foundApartmentList.get(0)).isEqualToComparingFieldByField(apartment);
    }

    @Test
    public void whenFindAll_thenReturnEmptyList() {
        List<Apartment> foundApartmentList = repository.findAll();
        assertThat(foundApartmentList).hasSize(0);
    }

    @Test
    public void whenFindById_thenReturnOneApartment() {
        Apartment apartment = new Apartment()
                .setNumber("number")
                .setPrice(123)
                .setAmountOfBeds(1)
                .setAmountOfRooms(1)
                .setIfBathroomExist(true);

        entityManager.persist(apartment);
        entityManager.flush();

        Optional<Apartment> foundApartment = repository.findById(apartment.getId());

        assertThat(foundApartment.get().getNumber()).isEqualTo(apartment.getNumber());
    }

    @Test
    public void whenSaveApartment_thenReturnApartment() {
        Apartment apartment = new Apartment()
                .setNumber("number")
                .setPrice(123)
                .setAmountOfBeds(1)
                .setAmountOfRooms(1)
                .setIfBathroomExist(true);

        entityManager.persist(apartment);
        entityManager.flush();

        Apartment savedApartment = repository.save(apartment);

        assertThat(savedApartment.getNumber()).isEqualTo(apartment.getNumber());
    }

    @Test
    public void whenDeleteApartment_thenVoid() {
        Apartment apartment = new Apartment()
                .setNumber("number")
                .setPrice(123)
                .setAmountOfBeds(1)
                .setAmountOfRooms(1)
                .setIfBathroomExist(true);

        entityManager.persist(apartment);
        entityManager.flush();

        repository.delete(apartment);

        Optional<Apartment> deletedApartment = repository.findById(apartment.getId());

        assertThat(deletedApartment).isEmpty();
    }
}
