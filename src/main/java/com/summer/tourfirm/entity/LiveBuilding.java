package com.summer.tourfirm.entity;

import com.summer.tourfirm.entity.enums.Building;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "liveBuildings")
public class LiveBuilding {

    private static final long serialVersionUID = -8748906876368098L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private ResortArea area;

    @NotNull
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<Apartment> apartments = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private Building type;

    private Integer availableApartmentCount;

    private Boolean ifPoolExist;

    private Boolean ifParkingExist;

    private Integer distanceToBeach;

    public LiveBuilding() {
    }

    public LiveBuilding(List<Apartment> apartments, Building type, Integer availableApartmentCount,
                        Boolean ifPoolExist, Boolean ifParkingExist, Integer distanceToBeach) {
        this.apartments = apartments;
        this.type = type;
        this.availableApartmentCount = availableApartmentCount;
        this.ifPoolExist = ifPoolExist;
        this.ifParkingExist = ifParkingExist;
        this.distanceToBeach = distanceToBeach;
    }

    public Long getId() {
        return id;
    }

    public LiveBuilding setId(Long id) {
        this.id = id;
        return this;
    }

    public ResortArea getArea() {
        return area;
    }

    public LiveBuilding setArea(ResortArea area) {
        this.area = area;
        return this;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public LiveBuilding setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
        return this;
    }

    public Building getType() {
        return type;
    }

    public LiveBuilding setType(Building type) {
        this.type = type;
        return this;
    }

    public Integer getAvailableApartmentCount() {
        return availableApartmentCount;
    }

    public LiveBuilding setAvailableApartmentCount(int availableApartmentCount) {
        this.availableApartmentCount = availableApartmentCount;
        return this;
    }

    public Boolean isIfPoolExist() {
        return ifPoolExist;
    }

    public LiveBuilding setIfPoolExist(boolean ifPoolExist) {
        this.ifPoolExist = ifPoolExist;
        return this;
    }

    public Boolean isIfParkingExist() {
        return ifParkingExist;
    }

    public LiveBuilding setIfParkingExist(boolean ifParkingExist) {
        this.ifParkingExist = ifParkingExist;
        return this;
    }

    public Integer getDistanceToBeach() {
        return distanceToBeach;
    }

    public LiveBuilding setDistanceToBeach(int distanceToBeach) {
        this.distanceToBeach = distanceToBeach;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LiveBuilding that = (LiveBuilding) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
