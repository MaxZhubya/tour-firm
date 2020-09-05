package com.summer.tourfirm.entity;

import com.summer.tourfirm.exception.DataNotFoundException;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "resort_areas")
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

    @NotEmpty
    private String name;

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

    public String getName() {
        return name;
    }

    public ResortArea setName(String name) {
        this.name = name;
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

    public LiveBuilding getBuildingByNumber(String number) {
        return getBuildings().stream().filter(value -> value.getNumber().equalsIgnoreCase(number))
                .findFirst().orElseThrow(() -> new DataNotFoundException("LiveBuilding with number: "
                        + number + " doesn't exist"));
    }
}
