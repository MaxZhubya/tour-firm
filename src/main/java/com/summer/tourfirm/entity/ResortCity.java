package com.summer.tourfirm.entity;

import com.summer.tourfirm.entity.types.EntranceType;
import com.summer.tourfirm.entity.types.TravelingType;
import com.summer.tourfirm.exception.DataNotFoundException;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "resort_cities")
public class ResortCity {

    private static final long serialVersionUID = -7689765126368098L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @NotNull
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<ResortArea> areas = new ArrayList<>();

    private Boolean isAbleForEntering;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "resort_city_enter_type",
            joinColumns = {
                    @JoinColumn(name = "city_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "enter_type_id", referencedColumnName = "id")
            })
    private List<EntranceType> enterTypes = new ArrayList<>();

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "resort_city_travel_type",
            joinColumns = {
                    @JoinColumn(name = "country_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "travel_type_id", referencedColumnName = "id")
            })
    private List<TravelingType> travelingTypes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public ResortCity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResortCity setName(String name) {
        this.name = name;
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

    public Boolean getIsAbleForEntering() {
        return isAbleForEntering;
    }

    public ResortCity setIsAbleForEntering(boolean isAbleForEntering) {
        this.isAbleForEntering = isAbleForEntering;
        return this;
    }

    public List<EntranceType> getEnterTypes() {
        return enterTypes;
    }

    public ResortCity setEnterTypes(List<EntranceType> enterTypes) {
        this.enterTypes = enterTypes;
        return this;
    }

    public List<TravelingType> getTravelingTypes() {
        return travelingTypes;
    }

    public ResortCity setTravelingTypes(List<TravelingType> travelingTypes) {
        this.travelingTypes = travelingTypes;
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

    public ResortArea getAreaByName(String name) {
        return getAreas().stream().filter(value -> value.getName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new DataNotFoundException("ResortArea with name: "
                        + name + " doesn't exist"));
    }
}
