package com.summer.tourfirm.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "apartments")
public class Apartment {

    private static final long serialVersionUID = -8748906885640287L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private LiveBuilding building;

    private Integer price;

    private Integer amountOfBeds;

    private Integer amountOfRooms;

    private Boolean ifBathroomExist;

    public Apartment() {
    }

    public Apartment(Integer price, Integer amountOfBeds, Integer amountOfRooms, Boolean ifBathroomExist) {
        this.price = price;
        this.amountOfBeds = amountOfBeds;
        this.amountOfRooms = amountOfRooms;
        this.ifBathroomExist = ifBathroomExist;
    }

    public Long getId() {
        return id;
    }

    public Apartment setId(Long id) {
        this.id = id;
        return this;
    }

    public LiveBuilding getBuilding() {
        return building;
    }

    public Apartment setBuilding(LiveBuilding building) {
        this.building = building;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Apartment setPrice(int price) {
        this.price = price;
        return this;
    }

    public Integer getAmountOfBeds() {
        return amountOfBeds;
    }

    public Apartment setAmountOfBeds(int amountOfBeds) {
        this.amountOfBeds = amountOfBeds;
        return this;
    }

    public Integer getAmountOfRooms() {
        return amountOfRooms;
    }

    public Apartment setAmountOfRooms(int amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
        return this;
    }

    public Boolean isIfBathroomExist() {
        return ifBathroomExist;
    }

    public Apartment setIfBathroomExist(boolean ifBathroomExist) {
        this.ifBathroomExist = ifBathroomExist;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return getId().equals(apartment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
