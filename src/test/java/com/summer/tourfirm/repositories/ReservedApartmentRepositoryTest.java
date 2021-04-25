package com.summer.tourfirm.repositories;

import com.summer.tourfirm.entity.ReservedApartment;
import com.summer.tourfirm.repository.ReservedApartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ReservedApartmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReservedApartmentRepository repository;

    @Test
    public void whenFindAll_thenReturnAllReservedApartments() {
        ReservedApartment reservedApartment = new ReservedApartment()
                .setDateIn(ZonedDateTime.now())
                .setDateOut(ZonedDateTime.now());

        entityManager.persist(reservedApartment);
        entityManager.flush();

        List<ReservedApartment> foundReservedApartmentList = repository.findAll();

        assertThat(foundReservedApartmentList.get(0)).isEqualToComparingFieldByField(reservedApartment);
    }

    @Test
    public void whenFindAll_thenReturnEmptyList() {
        List<ReservedApartment> foundReservedApartmentList = repository.findAll();
        assertThat(foundReservedApartmentList).hasSize(0);
    }

    @Test
    public void whenFindById_thenReturnOneReservedApartment() {
        ReservedApartment reservedApartment = new ReservedApartment()
                .setDateIn(ZonedDateTime.now())
                .setDateOut(ZonedDateTime.now());

        entityManager.persist(reservedApartment);
        entityManager.flush();

        Optional<ReservedApartment> foundReservedApartment = repository.findById(reservedApartment.getId());

        assertThat(foundReservedApartment.get().getDateIn()).isEqualTo(reservedApartment.getDateIn());
    }

    @Test
    public void whenSaveReservedApartment_thenReturnReservedApartment() {
        ReservedApartment reservedApartment = new ReservedApartment()
                .setDateIn(ZonedDateTime.now())
                .setDateOut(ZonedDateTime.now());

        entityManager.persist(reservedApartment);
        entityManager.flush();

        ReservedApartment savedReservedApartment = repository.save(reservedApartment);

        assertThat(savedReservedApartment.getDateIn()).isEqualTo(reservedApartment.getDateIn());
    }

    @Test
    public void whenDeleteReservedApartment_thenVoid() {
        ReservedApartment reservedApartment = new ReservedApartment()
                .setDateIn(ZonedDateTime.now())
                .setDateOut(ZonedDateTime.now());

        entityManager.persist(reservedApartment);
        entityManager.flush();

        repository.delete(reservedApartment);

        Optional<ReservedApartment> deletedReservedApartment = repository.findById(reservedApartment.getId());

        assertThat(deletedReservedApartment).isEmpty();
    }
}
