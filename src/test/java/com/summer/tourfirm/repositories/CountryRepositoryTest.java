package com.summer.tourfirm.repositories;

import com.summer.tourfirm.entity.Country;
import com.summer.tourfirm.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CountryRepository repository;

    @Test
    public void whenFindAll_thenReturnAllCountries() {
        Country country = new Country()
                .setAbleForEntering(false)
                .setName("Temp Country For Tests");

        entityManager.persist(country);
        entityManager.flush();

        List<Country> foundCountryList = repository.findAll();

        assertThat(foundCountryList.get(0)).isEqualToComparingFieldByField(country);
    }

    @Test
    public void whenFindAll_thenReturnEmptyList() {
        List<Country> foundCountryList = repository.findAll();
        assertThat(foundCountryList).hasSize(0);
    }

    @Test
    public void whenFindById_thenReturnOneCountry() {
        Country country = new Country()
                .setName("Test Country Name")
                .setAbleForEntering(true);

        entityManager.persist(country);
        entityManager.flush();

        Optional<Country> foundCountry = repository.findById(country.getId());

        assertThat(foundCountry.get().getName()).isEqualTo(country.getName());
    }

    @Test
    public void whenSaveCountry_thenReturnCountry() {
        Country country = new Country()
                .setName("Test Country Name")
                .setAbleForEntering(true);

        entityManager.persist(country);
        entityManager.flush();

        Country savedCountry = repository.save(country);

        assertThat(savedCountry.getName()).isEqualTo(country.getName());
    }

    @Test
    public void whenDeleteCountry_thenVoid() {
        Country country = new Country()
                .setName("Test Country Name")
                .setAbleForEntering(true);

        entityManager.persist(country);
        entityManager.flush();

        repository.delete(country);

        Optional<Country> deletedCountry = repository.findById(country.getId());

        assertThat(deletedCountry).isEmpty();
    }
}
