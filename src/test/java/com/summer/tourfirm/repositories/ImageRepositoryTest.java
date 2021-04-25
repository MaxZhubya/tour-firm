package com.summer.tourfirm.repositories;

import com.summer.tourfirm.entity.Image;
import com.summer.tourfirm.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ImageRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ImageRepository repository;

    @Test
    public void whenFindAll_thenReturnAllImages() {
        Image image = new Image()
                .setName("Temp Image For Tests");

        entityManager.persist(image);
        entityManager.flush();

        List<Image> foundImageList = repository.findAll();

        assertThat(foundImageList.get(0)).isEqualToComparingFieldByField(image);
    }

    @Test
    public void whenFindAll_thenReturnEmptyList() {
        List<Image> foundImageList = repository.findAll();
        assertThat(foundImageList).hasSize(0);
    }

    @Test
    public void whenFindById_thenReturnOneImage() {
        Image image = new Image()
                .setName("Test Image Name");

        entityManager.persist(image);
        entityManager.flush();

        Optional<Image> foundImage = repository.findById(image.getId());

        assertThat(foundImage.get().getName()).isEqualTo(image.getName());
    }

    @Test
    public void whenSaveImage_thenReturnImage() {
        Image image = new Image()
                .setName("Test Image Name");

        entityManager.persist(image);
        entityManager.flush();

        Image savedImage = repository.save(image);

        assertThat(savedImage.getName()).isEqualTo(image.getName());
    }

    @Test
    public void whenDeleteImage_thenVoid() {
        Image image = new Image()
                .setName("Test Image Name");

        entityManager.persist(image);
        entityManager.flush();

        repository.delete(image);

        Optional<Image> deletedImage = repository.findById(image.getId());

        assertThat(deletedImage).isEmpty();
    }
}
