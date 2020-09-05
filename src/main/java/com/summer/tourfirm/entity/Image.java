package com.summer.tourfirm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Table(name = "images")
public class Image {

    private static final long serialVersionUID = -4112332342344444L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private LiveBuilding building;

    @NotEmpty
    private String name;

    public Image() {
    }

    public Image(@NotEmpty String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Image setId(Long id) {
        this.id = id;
        return this;
    }

    public LiveBuilding getBuilding() {
        return building;
    }

    public Image setBuilding(LiveBuilding building) {
        this.building = building;
        return this;
    }

    public String getName() {
        return name;
    }

    public Image setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return getId().equals(image.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
