package com.summer.tourfirm.entity;

import com.summer.tourfirm.entity.enums.BuildingEnum;
import com.summer.tourfirm.exception.DataNotFoundException;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "live_buildings")
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
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private BuildingEnum type;

    @NotEmpty
    private String number;

    @NotEmpty
    private String address;

    @NotEmpty
    private String name;

    private Boolean ifPoolExist;

    private Boolean ifParkingExist;

    private Integer distanceToBeach;

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

    public List<Image> getImages() {
        return images;
    }

    public LiveBuilding setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public BuildingEnum getType() {
        return type;
    }

    public LiveBuilding setType(BuildingEnum type) {
        this.type = type;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public LiveBuilding setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public LiveBuilding setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getName() {
        return name;
    }

    public LiveBuilding setName(String name) {
        this.name = name;
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

    public Apartment getApartmentByNumber(String number) {
        return getApartments().stream().filter(value -> value.getNumber().equalsIgnoreCase(number))
                .findFirst().orElseThrow(() -> new DataNotFoundException("Apartment with number: "
                        + number + " doesn't exist"));
    }

    public Image getImageByName(String name) {
        return getImages().stream().filter(value -> value.getName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new DataNotFoundException("Image with name: "
                        + name + " doesn't exist"));
    }
}
