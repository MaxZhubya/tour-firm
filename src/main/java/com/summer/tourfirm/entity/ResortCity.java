package com.summer.tourfirm.entity;

import com.summer.tourfirm.entity.enums.EntranceWay;
import com.summer.tourfirm.entity.enums.TravelingWay;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "resortCities")
public class ResortCity {

    private static final long serialVersionUID = -7689765126368098L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @NotNull
    @OneToMany(mappedBy = "city")
    private List<ResortArea> areas = new ArrayList<>();

    private Boolean isAbleForEntering;

    @NotNull
    @Enumerated(EnumType.STRING)
    private List<EntranceWay> entranceWays = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private List<TravelingWay> travelingWays = new ArrayList<>();

    public ResortCity() {
    }

    public ResortCity(List<ResortArea> areas, Boolean isAbleForEntering, List<EntranceWay> entranceWays,
                      List<TravelingWay> travelingWays) {
        this.areas = areas;
        this.isAbleForEntering = isAbleForEntering;
        this.entranceWays = entranceWays;
        this.travelingWays = travelingWays;
    }

    public Long getId() {
        return id;
    }

    public ResortCity setId(Long id) {
        this.id = id;
        return this;
    }

    public Country getCountry() {
        return country;
    }

    public ResortCity setCountry(Country country) {
        this.country = country;
        return this;
    }

    public List<ResortArea> getAreas() {
        return areas;
    }

    public ResortCity setAreas(List<ResortArea> areas) {
        this.areas = areas;
        return this;
    }

    public Boolean isAbleForEntering() {
        return isAbleForEntering;
    }

    public ResortCity setAbleForEntering(boolean ableForEntering) {
        isAbleForEntering = ableForEntering;
        return this;
    }

    public List<EntranceWay> getEntranceWays() {
        return entranceWays;
    }

    public ResortCity setEntranceWays(List<EntranceWay> entranceWays) {
        this.entranceWays = entranceWays;
        return this;
    }

    public List<TravelingWay> getTravelingWays() {
        return travelingWays;
    }

    public ResortCity setTravelingWays(List<TravelingWay> travelingWays) {
        this.travelingWays = travelingWays;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResortCity that = (ResortCity) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
