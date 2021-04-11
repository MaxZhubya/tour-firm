package com.summer.tourfirm.repositories;

import com.summer.tourfirm.entity.Country;
import com.summer.tourfirm.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

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

}
