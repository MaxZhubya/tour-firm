package com.summer.tourfirm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "resortAreas")
public class ResortArea {

    private static final long serialVersionUID = -5678936876368098L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private ResortCity city;

    @NotNull
    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL)
    private List<LiveBuilding> buildings = new ArrayList<>();

    private String definition;

    public ResortArea() {
    }

    public ResortArea(List<LiveBuilding> buildings, String definition) {
        this.buildings = buildings;
        this.definition = definition;
    }

    public Long getId() {
        return id;
    }

    public ResortArea setId(Long id) {
        this.id = id;
        return this;
    }

    public ResortCity getCity() {
        return city;
    }

    public ResortArea setCity(ResortCity city) {
        this.city = city;
        return this;
    }

    public List<LiveBuilding> getBuildings() {
        return buildings;
    }

    public ResortArea setBuildings(List<LiveBuilding> buildings) {
        this.buildings = buildings;
        return this;
    }

    public String getDefinition() {
        return definition;
    }

    public ResortArea setDefinition(String definition) {
        this.definition = definition;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResortArea that = (ResortArea) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
