package com.summer.tourfirm.entity;

import com.summer.tourfirm.entity.enums.Entrance;
import com.summer.tourfirm.entity.types.EntranceType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "countries")
public class Country {

    private static final long serialVersionUID = -2389712126356988L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToMany(mappedBy = "country")
    private List<ResortCity> cities = new ArrayList<>();

    private Boolean isAbleForEntering;

    @NotNull
    @Enumerated(EnumType.STRING)
    private List<EntranceType> enterTypes = new ArrayList<>();

    public Country() {
    }

    public Country(List<ResortCity> cities, Boolean isAbleForEntering, List<Entrance> enterWays) {
        this.cities = cities;
        this.isAbleForEntering = isAbleForEntering;
        this.enterWays = enterWays;
    }

    public Long getId() {
        return id;
    }

    public Country setId(Long id) {
        this.id = id;
        return this;
    }

    public List<ResortCity> getCities() {
        return cities;
    }

    public Country setCities(List<ResortCity> cities) {
        this.cities = cities;
        return this;
    }

    public Boolean isAbleForEntering() {
        return isAbleForEntering;
    }

    public Country setAbleForEntering(boolean ableForEntering) {
        isAbleForEntering = ableForEntering;
        return this;
    }

    public List<Entrance> getEnterWays() {
        return enterWays;
    }

    public Country setEnterWays(List<Entrance> enterWays) {
        this.enterWays = enterWays;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return getId().equals(country.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
