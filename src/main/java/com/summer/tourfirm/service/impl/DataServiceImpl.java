package com.summer.tourfirm.service.impl;

import com.summer.tourfirm.entity.*;
import com.summer.tourfirm.entity.enums.BuildingEnum;
import com.summer.tourfirm.entity.types.EntranceType;
import com.summer.tourfirm.entity.types.TravelingType;
import com.summer.tourfirm.exception.ApplicationException;
import com.summer.tourfirm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class DataServiceImpl {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ResortCityRepository cityRepository;

    @Autowired
    private ResortAreaRepository areaRepository;

    @Autowired
    private LiveBuildingRepository liveBuildingRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ReservedApartmentRepository reservedApartmentRepository;

    /* Type Repositories */

    @Autowired
    private EntranceTypeRepository entranceTypeRepository;

    @Autowired
    private TravelingTypeRepository travelingTypeRepository;

    public void initDataBase() {
        try {

            addToCountryCities();

        } catch (Exception e) {
            throw new ApplicationException("Initiating DB Exception: " + e.getMessage());
        }
    }

    //  ************************    Functional methods    ***************************

    private List<EntranceType> addEntranceTypes() {
        List<EntranceType> entranceTypeList = new ArrayList<>();
        entranceTypeList.addAll(Arrays.asList(
                //  0
                new EntranceType("AUTO"),
                //  1
                new EntranceType("ON_FOOT"),
                //  2
                new EntranceType("PLANE"),
                //  3
                new EntranceType("SHIP"))
        );

        List<EntranceType> entranceTypeListPersisted = entranceTypeRepository.findAll();
        if (entranceTypeListPersisted.isEmpty())
            return entranceTypeRepository.saveAll(entranceTypeList);
        else
            return entranceTypeListPersisted;
    }

    private List<TravelingType> addTravelingTypes() {
        List<TravelingType> travelingTypeList = new ArrayList<>();
        travelingTypeList.addAll(Arrays.asList(
                //  0
                new TravelingType("ON_FOOT"),
                //  1
                new TravelingType("TAXI"),
                //  2
                new TravelingType("BUS"),
                //  3
                new TravelingType("BOAT")
        ));

        travelingTypeRepository.saveAll(travelingTypeList);

        return travelingTypeList;
    }

    private List<LiveBuilding> addToLiveBuildingApartmentsAndImages() {
        List<LiveBuilding> liveBuildingList = new ArrayList<>();
        liveBuildingList.addAll(Arrays.asList(

                //  ************    Ukraine    *************
                //  0
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("121-C")
                        .setAddress("Pavlo Dovzhenko St.")
                        .setName("Forest Dream")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(200),
                //  1
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("145-V")
                        .setAddress("Kelminetska St.")
                        .setName("Panorama")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(200),
                //  2
                new LiveBuilding()
                        .setType(BuildingEnum.VILLA)
                        .setNumber("415-KF")
                        .setAddress("Saint Patrick St.")
                        .setName("Tiny")
                        .setIfPoolExist(false)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(50),
                //  3
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("145-V")
                        .setAddress("Arbuzniy St.")
                        .setName("Gagarin")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(600),
                //  4
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("423-AS2")
                        .setAddress("Lesya Ukrainka St.")
                        .setName("Club")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(300),
                //  5
                new LiveBuilding()
                        .setType(BuildingEnum.COTTAGE)
                        .setNumber("28-3A")
                        .setAddress("Heroes of Maidan St.")
                        .setName("Bang & Olufsen")
                        .setIfPoolExist(false)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(1500),
                //  6
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("63-QW")
                        .setAddress("Taras Shevchenko St.")
                        .setName("Arcadia")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(700),
                //  7
                new LiveBuilding()
                        .setType(BuildingEnum.COTTAGE)
                        .setNumber("123-DA")
                        .setAddress("Sea St.")
                        .setName("Continental")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(400),


                //  ************    Great Britain    *************
                //  8
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("2873-HSD")
                        .setAddress("Angel lane St.")
                        .setName("Marriott Grosvenor House")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(200),
                //  9
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("123-DA")
                        .setAddress("Back Alley St.")
                        .setName("Central Park")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(150),
                //  10
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("827-GJ")
                        .setAddress("Carter Court St.")
                        .setName("Montcalm Royal")
                        .setIfPoolExist(false)
                        .setIfParkingExist(false)
                        .setDistanceToBeach(400),
                //  11
                new LiveBuilding()
                        .setType(BuildingEnum.COTTAGE)
                        .setNumber("345K")
                        .setAddress("Dowgate Hill St.")
                        .setName("Soho")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(500),
                //  12
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("11F4G")
                        .setAddress("Eldon St.")
                        .setName("Tottenham Court Road")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(100),
                //  13
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("123GDF")
                        .setAddress("Finch Lane St.")
                        .setName("Strand Palace")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(300),
                //  14
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("543ASD")
                        .setAddress("Hind Court St.")
                        .setName("Park Plaza")
                        .setIfPoolExist(true)
                        .setIfParkingExist(false)
                        .setDistanceToBeach(1400),
                //  15
                new LiveBuilding()
                        .setType(BuildingEnum.VILLA)
                        .setNumber("SDF123")
                        .setAddress("Inner Temple St.")
                        .setName("St Giles")
                        .setIfPoolExist(false)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(500),


                //  ************    USA    *************
                //  16
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("KDF74")
                        .setAddress("Iron St.")
                        .setName("Tahoe")
                        .setIfPoolExist(false)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(500),
                //  17
                new LiveBuilding()
                        .setType(BuildingEnum.COTTAGE)
                        .setNumber("KISD83")
                        .setAddress("Greg St.")
                        .setName("Malibu")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(200),
                //  18
                new LiveBuilding()
                        .setType(BuildingEnum.VILLA)
                        .setNumber("ASD213")
                        .setAddress("Gannt Rid St.")
                        .setName("Holiday Inn")
                        .setIfPoolExist(false)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(700),
                //  19
                new LiveBuilding()
                        .setType(BuildingEnum.VILLA)
                        .setNumber("SGRE532")
                        .setAddress("Farrow Ave.")
                        .setName("Lakeland village")
                        .setIfPoolExist(false)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(1200),
                //  20
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("GSC06")
                        .setAddress("David Way St.")
                        .setName("Cupertino")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(350),
                //  21
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("FSDF345")
                        .setAddress("Carlota St.")
                        .setName("Minner's camp")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(300),
                //  22
                new LiveBuilding()
                        .setType(BuildingEnum.HOTEL)
                        .setNumber("HXCS34")
                        .setAddress("Bader St.")
                        .setName("Comfort Inn")
                        .setIfPoolExist(false)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(200),
                //  23
                new LiveBuilding()
                        .setType(BuildingEnum.COTTAGE)
                        .setNumber("ASDSDF32")
                        .setAddress("Amethist St.")
                        .setName("Travelodge")
                        .setIfPoolExist(true)
                        .setIfParkingExist(true)
                        .setDistanceToBeach(500)
        ));


        //  ************    Apartment List    *************
        List<Apartment> apartmentList = new ArrayList<>();
        apartmentList.addAll(Arrays.asList(
                //  0
                new Apartment()
                        .setNumber("A1")
                        .setPrice(2000)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  1
                new Apartment()
                        .setNumber("A2")
                        .setPrice(1500)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  2
                new Apartment()
                        .setNumber("B1")
                        .setPrice(1500)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  3
                new Apartment()
                        .setNumber("B2")
                        .setPrice(3000)
                        .setAmountOfBeds(3)
                        .setAmountOfRooms(3)
                        .setIfBathroomExist(true),
                //  4
                new Apartment()
                        .setNumber("C1")
                        .setPrice(1500)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  5
                new Apartment()
                        .setNumber("C2")
                        .setPrice(500)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(1)
                        .setIfBathroomExist(false),
                //  6
                new Apartment()
                        .setNumber("V1")
                        .setPrice(1500)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  7
                new Apartment()
                        .setNumber("V2")
                        .setPrice(4000)
                        .setAmountOfBeds(3)
                        .setAmountOfRooms(4)
                        .setIfBathroomExist(true),
                //  8
                new Apartment()
                        .setNumber("S1")
                        .setPrice(1500)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  9
                new Apartment()
                        .setNumber("S2")
                        .setPrice(1000)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  10
                new Apartment()
                        .setNumber("SDS1")
                        .setPrice(1500)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  11
                new Apartment()
                        .setNumber("IU334")
                        .setPrice(3000)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(3)
                        .setIfBathroomExist(true),
                //  12
                new Apartment()
                        .setNumber("G345")
                        .setPrice(500)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(1)
                        .setIfBathroomExist(false),
                //  13
                new Apartment()
                        .setNumber("SD6")
                        .setPrice(2500)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  14
                new Apartment()
                        .setNumber("SDF23")
                        .setPrice(1500)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  15
                new Apartment()
                        .setNumber("AS23")
                        .setPrice(1000)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  16
                new Apartment()
                        .setNumber("ASD213")
                        .setPrice(4500)
                        .setAmountOfBeds(3)
                        .setAmountOfRooms(3)
                        .setIfBathroomExist(true),
                //  17
                new Apartment()
                        .setNumber("ASD3244")
                        .setPrice(1000)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  18
                new Apartment()
                        .setNumber("34223F")
                        .setPrice(2000)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(3)
                        .setIfBathroomExist(true),
                //  19
                new Apartment()
                        .setNumber("FDS552")
                        .setPrice(3500)
                        .setAmountOfBeds(3)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  20
                new Apartment()
                        .setNumber("47HJ")
                        .setPrice(100)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(1)
                        .setIfBathroomExist(false),
                //  21
                new Apartment()
                        .setNumber("87G")
                        .setPrice(1500)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(1)
                        .setIfBathroomExist(true),
                //  22
                new Apartment()
                        .setNumber("99J")
                        .setPrice(2000)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  23
                new Apartment()
                        .setNumber("9K")
                        .setPrice(1000)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(1)
                        .setIfBathroomExist(true),
                //  24
                new Apartment()
                        .setNumber("46H")
                        .setPrice(2500)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  25
                new Apartment()
                        .setNumber("356T")
                        .setPrice(3000)
                        .setAmountOfBeds(3)
                        .setAmountOfRooms(3)
                        .setIfBathroomExist(true),
                //  26
                new Apartment()
                        .setNumber("99L")
                        .setPrice(2000)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(3)
                        .setIfBathroomExist(true),
                //  27
                new Apartment()
                        .setNumber("17UI")
                        .setPrice(4000)
                        .setAmountOfBeds(3)
                        .setAmountOfRooms(4)
                        .setIfBathroomExist(true),
                //  28
                new Apartment()
                        .setNumber("7F")
                        .setPrice(1000)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(1)
                        .setIfBathroomExist(true),
                //  29
                new Apartment()
                        .setNumber("9K")
                        .setPrice(800)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(1)
                        .setIfBathroomExist(false),
                //  30
                new Apartment()
                        .setNumber("213K")
                        .setPrice(1000)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(3)
                        .setIfBathroomExist(true),
                //  31
                new Apartment()
                        .setNumber("9K")
                        .setPrice(1000)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(1)
                        .setIfBathroomExist(true),
                //  32
                new Apartment()
                        .setNumber("77H")
                        .setPrice(2000)
                        .setAmountOfBeds(3)
                        .setAmountOfRooms(4)
                        .setIfBathroomExist(true),
                //  33
                new Apartment()
                        .setNumber("12A")
                        .setPrice(3000)
                        .setAmountOfBeds(3)
                        .setAmountOfRooms(4)
                        .setIfBathroomExist(true),
                //  34
                new Apartment()
                        .setNumber("89H")
                        .setPrice(3000)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  35
                new Apartment()
                        .setNumber("89F")
                        .setPrice(500)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(1)
                        .setIfBathroomExist(false),
                //  36
                new Apartment()
                        .setNumber("13A")
                        .setPrice(1200)
                        .setAmountOfBeds(3)
                        .setAmountOfRooms(1)
                        .setIfBathroomExist(true),
                //  37
                new Apartment()
                        .setNumber("77F")
                        .setPrice(3000)
                        .setAmountOfBeds(3)
                        .setAmountOfRooms(4)
                        .setIfBathroomExist(true),
                //  38
                new Apartment()
                        .setNumber("38S")
                        .setPrice(2300)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(3)
                        .setIfBathroomExist(true),
                //  39
                new Apartment()
                        .setNumber("7G")
                        .setPrice(2200)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  40
                new Apartment()
                        .setNumber("67F")
                        .setPrice(4000)
                        .setAmountOfBeds(3)
                        .setAmountOfRooms(3)
                        .setIfBathroomExist(true),
                //  41
                new Apartment()
                        .setNumber("88G")
                        .setPrice(3000)
                        .setAmountOfBeds(3)
                        .setAmountOfRooms(3)
                        .setIfBathroomExist(true),
                //  42
                new Apartment()
                        .setNumber("4XD")
                        .setPrice(3000)
                        .setAmountOfBeds(3)
                        .setAmountOfRooms(3)
                        .setIfBathroomExist(true),
                //  43
                new Apartment()
                        .setNumber("97J")
                        .setPrice(800)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(1)
                        .setIfBathroomExist(false),
                //  44
                new Apartment()
                        .setNumber("878V")
                        .setPrice(1400)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(1)
                        .setIfBathroomExist(true),
                //  45
                new Apartment()
                        .setNumber("92N")
                        .setPrice(2000)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(3)
                        .setIfBathroomExist(true),
                //  46
                new Apartment()
                        .setNumber("19Z")
                        .setPrice(1000)
                        .setAmountOfBeds(1)
                        .setAmountOfRooms(2)
                        .setIfBathroomExist(true),
                //  47
                new Apartment()
                        .setNumber("10GF")
                        .setPrice(1500)
                        .setAmountOfBeds(2)
                        .setAmountOfRooms(1)
                        .setIfBathroomExist(true)
        ));
//        apartmentList.forEach(apartment -> apartment.setBuilding(liveBuildingList.get(0)));

        liveBuildingList.get(0).getApartments().addAll(Arrays.asList(apartmentList.get(0), apartmentList.get(1)));
        liveBuildingList.get(1).getApartments().addAll(Arrays.asList(apartmentList.get(2), apartmentList.get(3)));
        liveBuildingList.get(2).getApartments().addAll(Arrays.asList(apartmentList.get(4), apartmentList.get(5)));
        liveBuildingList.get(3).getApartments().addAll(Arrays.asList(apartmentList.get(6), apartmentList.get(7)));
        liveBuildingList.get(4).getApartments().addAll(Arrays.asList(apartmentList.get(8), apartmentList.get(9)));
        liveBuildingList.get(5).getApartments().addAll(Arrays.asList(apartmentList.get(10), apartmentList.get(11)));
        liveBuildingList.get(6).getApartments().addAll(Arrays.asList(apartmentList.get(12), apartmentList.get(13)));
        liveBuildingList.get(7).getApartments().addAll(Arrays.asList(apartmentList.get(14), apartmentList.get(15)));
        liveBuildingList.get(8).getApartments().addAll(Arrays.asList(apartmentList.get(16), apartmentList.get(17)));
        liveBuildingList.get(9).getApartments().addAll(Arrays.asList(apartmentList.get(18), apartmentList.get(19)));
        liveBuildingList.get(10).getApartments().addAll(Arrays.asList(apartmentList.get(20), apartmentList.get(21)));
        liveBuildingList.get(11).getApartments().addAll(Arrays.asList(apartmentList.get(22), apartmentList.get(23)));
        liveBuildingList.get(12).getApartments().addAll(Arrays.asList(apartmentList.get(24), apartmentList.get(25)));
        liveBuildingList.get(13).getApartments().addAll(Arrays.asList(apartmentList.get(26), apartmentList.get(27)));
        liveBuildingList.get(14).getApartments().addAll(Arrays.asList(apartmentList.get(28), apartmentList.get(29)));
        liveBuildingList.get(15).getApartments().addAll(Arrays.asList(apartmentList.get(30), apartmentList.get(31)));
        liveBuildingList.get(16).getApartments().addAll(Arrays.asList(apartmentList.get(32), apartmentList.get(33)));
        liveBuildingList.get(17).getApartments().addAll(Arrays.asList(apartmentList.get(34), apartmentList.get(35)));
        liveBuildingList.get(18).getApartments().addAll(Arrays.asList(apartmentList.get(36), apartmentList.get(37)));
        liveBuildingList.get(19).getApartments().addAll(Arrays.asList(apartmentList.get(38), apartmentList.get(39)));
        liveBuildingList.get(20).getApartments().addAll(Arrays.asList(apartmentList.get(40), apartmentList.get(41)));
        liveBuildingList.get(21).getApartments().addAll(Arrays.asList(apartmentList.get(42), apartmentList.get(43)));
        liveBuildingList.get(22).getApartments().addAll(Arrays.asList(apartmentList.get(44), apartmentList.get(45)));
        liveBuildingList.get(23).getApartments().addAll(Arrays.asList(apartmentList.get(46), apartmentList.get(47)));

        // Add needed Building to internal Apartments
        liveBuildingList.forEach(value -> {
            for (Apartment apartment: value.getApartments()) {
                apartment.setBuilding(value);
            }
        });

//        apartmentList.get(0).setBuilding(liveBuildingList.get(0));
//        apartmentList.get(1).setBuilding(liveBuildingList.get(0));
//        apartmentList.get(2).setBuilding(liveBuildingList.get(1));
//        apartmentList.get(3).setBuilding(liveBuildingList.get(1));
//        apartmentList.get(4).setBuilding(liveBuildingList.get(2));
//        apartmentList.get(5).setBuilding(liveBuildingList.get(2));
//        apartmentList.get(6).setBuilding(liveBuildingList.get(3));
//        apartmentList.get(7).setBuilding(liveBuildingList.get(3));
//        apartmentList.get(8).setBuilding(liveBuildingList.get(4));
//        apartmentList.get(9).setBuilding(liveBuildingList.get(4));
//        apartmentList.get(10).setBuilding(liveBuildingList.get(5));
//        apartmentList.get(11).setBuilding(liveBuildingList.get(5));
//        apartmentList.get(12).setBuilding(liveBuildingList.get(6));
//        apartmentList.get(13).setBuilding(liveBuildingList.get(6));
//        apartmentList.get(14).setBuilding(liveBuildingList.get(7));
//        apartmentList.get(15).setBuilding(liveBuildingList.get(7));
//        apartmentList.get(16).setBuilding(liveBuildingList.get(8));
//        apartmentList.get(17).setBuilding(liveBuildingList.get(8));
//        apartmentList.get(18).setBuilding(liveBuildingList.get(9));
//        apartmentList.get(19).setBuilding(liveBuildingList.get(9));
//        apartmentList.get(20).setBuilding(liveBuildingList.get(10));
//        apartmentList.get(21).setBuilding(liveBuildingList.get(10));
//        apartmentList.get(22).setBuilding(liveBuildingList.get(11));
//        apartmentList.get(23).setBuilding(liveBuildingList.get(11));
//        apartmentList.get(24).setBuilding(liveBuildingList.get(12));
//        apartmentList.get(25).setBuilding(liveBuildingList.get(12));
//        apartmentList.get(26).setBuilding(liveBuildingList.get(13));
//        apartmentList.get(27).setBuilding(liveBuildingList.get(13));
//        apartmentList.get(28).setBuilding(liveBuildingList.get(14));
//        apartmentList.get(29).setBuilding(liveBuildingList.get(14));
//        apartmentList.get(30).setBuilding(liveBuildingList.get(15));
//        apartmentList.get(31).setBuilding(liveBuildingList.get(15));
//        apartmentList.get(32).setBuilding(liveBuildingList.get(16));
//        apartmentList.get(33).setBuilding(liveBuildingList.get(16));
//        apartmentList.get(34).setBuilding(liveBuildingList.get(17));
//        apartmentList.get(35).setBuilding(liveBuildingList.get(17));
//        apartmentList.get(36).setBuilding(liveBuildingList.get(18));
//        apartmentList.get(37).setBuilding(liveBuildingList.get(18));
//        apartmentList.get(38).setBuilding(liveBuildingList.get(19));
//        apartmentList.get(39).setBuilding(liveBuildingList.get(19));
//        apartmentList.get(40).setBuilding(liveBuildingList.get(20));
//        apartmentList.get(41).setBuilding(liveBuildingList.get(20));
//        apartmentList.get(42).setBuilding(liveBuildingList.get(21));
//        apartmentList.get(43).setBuilding(liveBuildingList.get(21));
//        apartmentList.get(44).setBuilding(liveBuildingList.get(22));
//        apartmentList.get(45).setBuilding(liveBuildingList.get(22));
//        apartmentList.get(46).setBuilding(liveBuildingList.get(23));
//        apartmentList.get(47).setBuilding(liveBuildingList.get(23));


        //  ************    Image List    *************
        List<Image> imageList = new ArrayList<>();
        imageList.addAll(Arrays.asList(

                //  0 - 0
                new Image()
                        .setName("260737676.jpg"),
//                        .setBuilding(liveBuildingList.get(0)),
                new Image()
                        .setName("260737698.jpg"),
//                        .setBuilding(liveBuildingList.get(0)),
                new Image()
                        .setName("260737668.jpg"),
//                        .setBuilding(liveBuildingList.get(0)),
                new Image()
                        .setName("260737689.jpg"),
//                        .setBuilding(liveBuildingList.get(0)),
                new Image()
                        .setName("257492043.jpg"),
//                        .setBuilding(liveBuildingList.get(0)),
                new Image()
                        .setName("176676655.jpg"),
//                        .setBuilding(liveBuildingList.get(0)),
                new Image()
                        .setName("176676535.jpg"),
//                        .setBuilding(liveBuildingList.get(0)),
                new Image()
                        .setName("176676637.jpg"),
//                        .setBuilding(liveBuildingList.get(0)),

                //  1 - 8
                new Image()
                        .setName("260914782.jpg"),
//                        .setBuilding(liveBuildingList.get(1)),
                new Image()
                        .setName("260304038.jpg"),
//                        .setBuilding(liveBuildingList.get(1)),
                new Image()
                        .setName("240613273.jpg"),
//                        .setBuilding(liveBuildingList.get(1)),
                new Image()
                        .setName("260925322.jpg"),
//                        .setBuilding(liveBuildingList.get(1)),
                new Image()
                        .setName("260926227.jpg"),
//                        .setBuilding(liveBuildingList.get(1)),
                new Image()
                        .setName("240612660.jpg"),
//                        .setBuilding(liveBuildingList.get(1)),

                //  2 - 14
                new Image()
                        .setName("219237953.jpg"),
//                        .setBuilding(liveBuildingList.get(2)),
                new Image()
                        .setName("215356395.jpg"),
//                        .setBuilding(liveBuildingList.get(2)),
                new Image()
                        .setName("218862979.jpg"),
//                        .setBuilding(liveBuildingList.get(2)),
                new Image()
                        .setName("219238670.jpg"),
//                        .setBuilding(liveBuildingList.get(2)),
                new Image()
                        .setName("219241895.jpg"),
//                        .setBuilding(liveBuildingList.get(2)),
                new Image()
                        .setName("219243072.jpg"),
//                        .setBuilding(liveBuildingList.get(2)),
                new Image()
                        .setName("218805019.jpg"),
//                        .setBuilding(liveBuildingList.get(2)),
                new Image()
                        .setName("219238665.jpg"),
//                        .setBuilding(liveBuildingList.get(2)),
                new Image()
                        .setName("218810313.jpg"),
//                        .setBuilding(liveBuildingList.get(2)),

                //  3 - 23
                new Image()
                        .setName("76695828.jpg"),
//                        .setBuilding(liveBuildingList.get(3)),
                new Image()
                        .setName("256314877.jpg"),
//                        .setBuilding(liveBuildingList.get(3)),
                new Image()
                        .setName("76694847.jpg"),
//                        .setBuilding(liveBuildingList.get(3)),
                new Image()
                        .setName("76694810.jpg"),
//                        .setBuilding(liveBuildingList.get(3)),

                //  4 - 27
                new Image()
                        .setName("267867251.jpg"),
//                        .setBuilding(liveBuildingList.get(4)),
                new Image()
                        .setName("164182786.jpg"),
//                        .setBuilding(liveBuildingList.get(4)),
                new Image()
                        .setName("63042166.jpg"),
//                        .setBuilding(liveBuildingList.get(4)),
                new Image()
                        .setName("63044523.jpg"),
//                        .setBuilding(liveBuildingList.get(4)),
                new Image()
                        .setName("129109710.jpg"),
//                        .setBuilding(liveBuildingList.get(4)),

                //  5 - 32
                new Image()
                        .setName("51729276.jpg"),
//                        .setBuilding(liveBuildingList.get(5)),
                new Image()
                        .setName("118281676.jpg"),
//                        .setBuilding(liveBuildingList.get(5)),
                new Image()
                        .setName("54470343.jpg"),
//                        .setBuilding(liveBuildingList.get(5)),
                new Image()
                        .setName("101695114.jpg"),
//                        .setBuilding(liveBuildingList.get(5)),
                new Image()
                        .setName("215411638.jpg"),
//                        .setBuilding(liveBuildingList.get(5)),

                //  6 - 37
                new Image()
                        .setName("263366333.jpg"),
//                        .setBuilding(liveBuildingList.get(6)),
                new Image()
                        .setName("263366346.jpg"),
//                        .setBuilding(liveBuildingList.get(6)),
                new Image()
                        .setName("263366322.jpg"),
//                        .setBuilding(liveBuildingList.get(6)),
                new Image()
                        .setName("263366405.jpg"),
//                        .setBuilding(liveBuildingList.get(6)),
                new Image()
                        .setName("263366172.jpg"),
//                        .setBuilding(liveBuildingList.get(6)),
                new Image()
                        .setName("263366182.jpg"),
//                        .setBuilding(liveBuildingList.get(6)),

                //  7 - 43
                new Image()
                        .setName("190035927.jpg"),
//                        .setBuilding(liveBuildingList.get(7)),
                new Image()
                        .setName("100408683.jpg"),
//                        .setBuilding(liveBuildingList.get(7)),
                new Image()
                        .setName("11026838.jpg"),
//                        .setBuilding(liveBuildingList.get(7)),
                new Image()
                        .setName("100516101.jpg"),
//                        .setBuilding(liveBuildingList.get(7)),
                new Image()
                        .setName("190035924.jpg"),
//                        .setBuilding(liveBuildingList.get(7)),

                //  8 - 48
                new Image()
                        .setName("221438181.jpg"),
//                        .setBuilding(liveBuildingList.get(8)),
                new Image()
                        .setName("194317331.jpg"),
//                        .setBuilding(liveBuildingList.get(8)),
                new Image()
                        .setName("170659077.jpg"),
//                        .setBuilding(liveBuildingList.get(8)),
                new Image()
                        .setName("267479707.jpg"),
//                        .setBuilding(liveBuildingList.get(8)),
                new Image()
                        .setName("183081243.jpg"),
//                        .setBuilding(liveBuildingList.get(8)),

                //  9 - 53
                new Image()
                        .setName("262031096.jpg"),
//                        .setBuilding(liveBuildingList.get(9)),
                new Image()
                        .setName("253212315.jpg"),
//                        .setBuilding(liveBuildingList.get(9)),
                new Image()
                        .setName("253212496.jpg"),
//                        .setBuilding(liveBuildingList.get(9)),
                new Image()
                        .setName("181795119.jpg"),
//                        .setBuilding(liveBuildingList.get(9)),
                new Image()
                        .setName("190404350.jpg"),
//                        .setBuilding(liveBuildingList.get(9)),

                //  10 - 58
                new Image()
                        .setName("261430505.jpg"),
//                        .setBuilding(liveBuildingList.get(10)),
                new Image()
                        .setName("50269827.jpg"),
//                        .setBuilding(liveBuildingList.get(10)),
                new Image()
                        .setName("257182404.jpg"),
//                        .setBuilding(liveBuildingList.get(10)),
                new Image()
                        .setName("261429930.jpg"),
//                        .setBuilding(liveBuildingList.get(10)),
                new Image()
                        .setName("256876131.jpg"),
//                        .setBuilding(liveBuildingList.get(10)),

                //  11 - 63
                new Image()
                        .setName("231391713.jpg"),
//                        .setBuilding(liveBuildingList.get(11)),
                new Image()
                        .setName("231379354.jpg"),
//                        .setBuilding(liveBuildingList.get(11)),
                new Image()
                        .setName("234822501.jpg"),
//                        .setBuilding(liveBuildingList.get(11)),
                new Image()
                        .setName("234821540.jpg"),
//                        .setBuilding(liveBuildingList.get(11)),
                new Image()
                        .setName("231383167.jpg"),
//                        .setBuilding(liveBuildingList.get(11)),
                new Image()
                        .setName("231383387.jpg"),
//                        .setBuilding(liveBuildingList.get(11)),

                //  12 - 69
                new Image()
                        .setName("260918010.jpg"),
//                        .setBuilding(liveBuildingList.get(12)),
                new Image()
                        .setName("260921020.jpg"),
//                        .setBuilding(liveBuildingList.get(12)),
                new Image()
                        .setName("260921011.jpg"),
//                        .setBuilding(liveBuildingList.get(12)),
                new Image()
                        .setName("260921384.jpg"),
//                        .setBuilding(liveBuildingList.get(12)),
                new Image()
                        .setName("260917965.jpg"),
//                        .setBuilding(liveBuildingList.get(12)),

                //  13 - 74
                new Image()
                        .setName("261456592.jpg"),
//                        .setBuilding(liveBuildingList.get(13)),
                new Image()
                        .setName("263289405.jpg"),
//                        .setBuilding(liveBuildingList.get(13)),
                new Image()
                        .setName("263287801.jpg"),
//                        .setBuilding(liveBuildingList.get(13)),
                new Image()
                        .setName("261456652.jpg"),
//                        .setBuilding(liveBuildingList.get(13)),
                new Image()
                        .setName("261456659.jpg"),
//                        .setBuilding(liveBuildingList.get(13)),

                //  14 - 79
                new Image()
                        .setName("260737676.jpg"),
//                        .setBuilding(liveBuildingList.get(14)),
                new Image()
                        .setName("260737698.jpg"),
//                        .setBuilding(liveBuildingList.get(14)),
                new Image()
                        .setName("260737668.jpg"),
//                        .setBuilding(liveBuildingList.get(14)),
                new Image()
                        .setName("260737689.jpg"),
//                        .setBuilding(liveBuildingList.get(14)),
                new Image()
                        .setName("257492043.jpg"),
//                        .setBuilding(liveBuildingList.get(14)),
                new Image()
                        .setName("176676655.jpg"),
//                        .setBuilding(liveBuildingList.get(14)),
                new Image()
                        .setName("176676535.jpg"),
//                        .setBuilding(liveBuildingList.get(14)),
                new Image()
                        .setName("176676637.jpg"),
//                        .setBuilding(liveBuildingList.get(14)),

                //  15 - 87
                new Image()
                        .setName("260914782.jpg"),
//                        .setBuilding(liveBuildingList.get(15)),
                new Image()
                        .setName("260304038.jpg"),
//                        .setBuilding(liveBuildingList.get(15)),
                new Image()
                        .setName("240613273.jpg"),
//                        .setBuilding(liveBuildingList.get(15)),
                new Image()
                        .setName("260925322.jpg"),
//                        .setBuilding(liveBuildingList.get(15)),
                new Image()
                        .setName("260926227.jpg"),
//                        .setBuilding(liveBuildingList.get(15)),
                new Image()
                        .setName("240612660.jpg"),
//                        .setBuilding(liveBuildingList.get(15)),

                //  16 - 93
                new Image()
                        .setName("219237953.jpg"),
//                        .setBuilding(liveBuildingList.get(16)),
                new Image()
                        .setName("215356395.jpg"),
//                        .setBuilding(liveBuildingList.get(16)),
                new Image()
                        .setName("218862979.jpg"),
//                        .setBuilding(liveBuildingList.get(16)),
                new Image()
                        .setName("219238670.jpg"),
//                        .setBuilding(liveBuildingList.get(16)),
                new Image()
                        .setName("219241895.jpg"),
//                        .setBuilding(liveBuildingList.get(16)),
                new Image()
                        .setName("219243072.jpg"),
//                        .setBuilding(liveBuildingList.get(16)),
                new Image()
                        .setName("218805019.jpg"),
//                        .setBuilding(liveBuildingList.get(16)),
                new Image()
                        .setName("219238665.jpg"),
//                        .setBuilding(liveBuildingList.get(16)),
                new Image()
                        .setName("218810313.jpg"),
//                        .setBuilding(liveBuildingList.get(16)),

                //  17 - 102
                new Image()
                        .setName("76695828.jpg"),
//                        .setBuilding(liveBuildingList.get(17)),
                new Image()
                        .setName("256314877.jpg"),
//                        .setBuilding(liveBuildingList.get(17)),
                new Image()
                        .setName("76694847.jpg"),
//                        .setBuilding(liveBuildingList.get(17)),
                new Image()
                        .setName("76694810.jpg"),
//                        .setBuilding(liveBuildingList.get(17)),

                //  18 - 106
                new Image()
                        .setName("267867251.jpg"),
//                        .setBuilding(liveBuildingList.get(18)),
                new Image()
                        .setName("164182786.jpg"),
//                        .setBuilding(liveBuildingList.get(18)),
                new Image()
                        .setName("63042166.jpg"),
//                        .setBuilding(liveBuildingList.get(18)),
                new Image()
                        .setName("63044523.jpg"),
//                        .setBuilding(liveBuildingList.get(18)),
                new Image()
                        .setName("129109710.jpg"),
//                        .setBuilding(liveBuildingList.get(18)),

                //  19 - 111
                new Image()
                        .setName("51729276.jpg"),
//                        .setBuilding(liveBuildingList.get(19)),
                new Image()
                        .setName("118281676.jpg"),
//                        .setBuilding(liveBuildingList.get(19)),
                new Image()
                        .setName("54470343.jpg"),
//                        .setBuilding(liveBuildingList.get(19)),
                new Image()
                        .setName("101695114.jpg"),
//                        .setBuilding(liveBuildingList.get(19)),
                new Image()
                        .setName("215411638.jpg"),
//                        .setBuilding(liveBuildingList.get(19)),

                //  20 - 116
                new Image()
                        .setName("263366333.jpg"),
//                        .setBuilding(liveBuildingList.get(20)),
                new Image()
                        .setName("263366346.jpg"),
//                        .setBuilding(liveBuildingList.get(20)),
                new Image()
                        .setName("263366322.jpg"),
//                        .setBuilding(liveBuildingList.get(20)),
                new Image()
                        .setName("263366405.jpg"),
//                        .setBuilding(liveBuildingList.get(20)),
                new Image()
                        .setName("263366172.jpg"),
//                        .setBuilding(liveBuildingList.get(20)),
                new Image()
                        .setName("263366182.jpg"),
//                        .setBuilding(liveBuildingList.get(20)),

                //  21 - 122
                new Image()
                        .setName("190035927.jpg"),
//                        .setBuilding(liveBuildingList.get(21)),
                new Image()
                        .setName("100408683.jpg"),
//                        .setBuilding(liveBuildingList.get(21)),
                new Image()
                        .setName("11026838.jpg"),
//                        .setBuilding(liveBuildingList.get(21)),
                new Image()
                        .setName("100516101.jpg"),
//                        .setBuilding(liveBuildingList.get(21)),
                new Image()
                        .setName("190035924.jpg"),
//                        .setBuilding(liveBuildingList.get(21)),

                //  22 - 127
                new Image()
                        .setName("221438181.jpg"),
//                        .setBuilding(liveBuildingList.get(22)),
                new Image()
                        .setName("194317331.jpg"),
//                        .setBuilding(liveBuildingList.get(22)),
                new Image()
                        .setName("170659077.jpg"),
//                        .setBuilding(liveBuildingList.get(22)),
                new Image()
                        .setName("267479707.jpg"),
//                        .setBuilding(liveBuildingList.get(22)),
                new Image()
                        .setName("183081243.jpg"),
//                        .setBuilding(liveBuildingList.get(22)),

                //  23 - 132
                new Image()
                        .setName("262031096.jpg"),
//                        .setBuilding(liveBuildingList.get(23)),
                new Image()
                        .setName("253212315.jpg"),
//                        .setBuilding(liveBuildingList.get(23)),
                new Image()
                        .setName("253212496.jpg"),
//                        .setBuilding(liveBuildingList.get(23)),
                new Image()
                        .setName("181795119.jpg"),
//                        .setBuilding(liveBuildingList.get(23)),
                new Image()
                        .setName("190404350.jpg")
//                        .setBuilding(liveBuildingList.get(23))
        ));
                        //  137 images


        liveBuildingList.get(0).getImages().addAll(Arrays.asList(
                imageList.get(0), imageList.get(1), imageList.get(2), imageList.get(3), imageList.get(4), imageList.get(5),
                imageList.get(6), imageList.get(7)
        ));

        liveBuildingList.get(1).getImages().addAll(Arrays.asList(
                imageList.get(8), imageList.get(9), imageList.get(10), imageList.get(11), imageList.get(12), imageList.get(13)
        ));

        liveBuildingList.get(2).getImages().addAll(Arrays.asList(
                imageList.get(14), imageList.get(15), imageList.get(16), imageList.get(17), imageList.get(18), imageList.get(19),
                imageList.get(20), imageList.get(21), imageList.get(22)
        ));

        liveBuildingList.get(3).getImages().addAll(Arrays.asList(
                imageList.get(23), imageList.get(24), imageList.get(25), imageList.get(26)
        ));

        liveBuildingList.get(4).getImages().addAll(Arrays.asList(
                imageList.get(27), imageList.get(28), imageList.get(29), imageList.get(30), imageList.get(31)
        ));

        liveBuildingList.get(5).getImages().addAll(Arrays.asList(
                imageList.get(32), imageList.get(33), imageList.get(34), imageList.get(35), imageList.get(36)
        ));

        liveBuildingList.get(6).getImages().addAll(Arrays.asList(
                imageList.get(37), imageList.get(38), imageList.get(39), imageList.get(40), imageList.get(41), imageList.get(42)
        ));

        liveBuildingList.get(7).getImages().addAll(Arrays.asList(
           imageList.get(43), imageList.get(44), imageList.get(45), imageList.get(46), imageList.get(47)
        ));

        liveBuildingList.get(8).getImages().addAll(Arrays.asList(
                imageList.get(48), imageList.get(49), imageList.get(50), imageList.get(51), imageList.get(52)
        ));

        liveBuildingList.get(9).getImages().addAll(Arrays.asList(
                imageList.get(53), imageList.get(54), imageList.get(55), imageList.get(56), imageList.get(57)
        ));

        liveBuildingList.get(10).getImages().addAll(Arrays.asList(
                imageList.get(58), imageList.get(59), imageList.get(60), imageList.get(61), imageList.get(62)
        ));

        liveBuildingList.get(11).getImages().addAll(Arrays.asList(
                imageList.get(63), imageList.get(64), imageList.get(65), imageList.get(66), imageList.get(67), imageList.get(68)
        ));

        liveBuildingList.get(12).getImages().addAll(Arrays.asList(
                imageList.get(69), imageList.get(70), imageList.get(71), imageList.get(72), imageList.get(73)
        ));

        liveBuildingList.get(13).getImages().addAll(Arrays.asList(
                imageList.get(74), imageList.get(75), imageList.get(76), imageList.get(77), imageList.get(78)
        ));

        liveBuildingList.get(14).getImages().addAll(Arrays.asList(
                imageList.get(79), imageList.get(80), imageList.get(81), imageList.get(82), imageList.get(83), imageList.get(84),
                imageList.get(85), imageList.get(86)
        ));

        liveBuildingList.get(15).getImages().addAll(Arrays.asList(
                imageList.get(87), imageList.get(88), imageList.get(89), imageList.get(90), imageList.get(91), imageList.get(92)
        ));

        liveBuildingList.get(16).getImages().addAll(Arrays.asList(
                imageList.get(93), imageList.get(94), imageList.get(95), imageList.get(96), imageList.get(97), imageList.get(98),
                imageList.get(99), imageList.get(100), imageList.get(101)
        ));

        liveBuildingList.get(17).getImages().addAll(Arrays.asList(
                imageList.get(102), imageList.get(103), imageList.get(104), imageList.get(105)
        ));

        liveBuildingList.get(18).getImages().addAll(Arrays.asList(
                imageList.get(106), imageList.get(107), imageList.get(108), imageList.get(109), imageList.get(110)
        ));

        liveBuildingList.get(19).getImages().addAll(Arrays.asList(
                imageList.get(111), imageList.get(112), imageList.get(113), imageList.get(114), imageList.get(115)
        ));

        liveBuildingList.get(20).getImages().addAll(Arrays.asList(
                imageList.get(116), imageList.get(117), imageList.get(118), imageList.get(119), imageList.get(120), imageList.get(121)
        ));

        liveBuildingList.get(21).getImages().addAll(Arrays.asList(
                imageList.get(122), imageList.get(123), imageList.get(124), imageList.get(125), imageList.get(126)
        ));

        liveBuildingList.get(22).getImages().addAll(Arrays.asList(
                imageList.get(127), imageList.get(128), imageList.get(129), imageList.get(130), imageList.get(131)
        ));

        liveBuildingList.get(23).getImages().addAll(Arrays.asList(
                imageList.get(132), imageList.get(133), imageList.get(134), imageList.get(135), imageList.get(136)
        ));

        // Add needed Building to internal Images
        liveBuildingList.forEach(value -> {
            for (Image image: value.getImages()) {
                image.setBuilding(value);
            }
        });

//        apartmentRepository.saveAll(apartmentList);
//        imageRepository.saveAll(imageList);

        return liveBuildingList;
    }

    private List<ResortCity> addToCityAreasAndLiveBuildings() {

        //  EntranceTypeList
        List<EntranceType> entranceTypeList = addEntranceTypes();

        //  TravelingType List
        List<TravelingType> travelingTypeList = addTravelingTypes();

        //  LiveBuilding List
        List<LiveBuilding> liveBuildingList = addToLiveBuildingApartmentsAndImages();

        //  ***     Creating ResortAreaList     ***
        List<ResortArea> resortAreaList = new ArrayList<>();
        resortAreaList.addAll(Arrays.asList(

                //  ************    Ukraine    *************
                //  0
                new ResortArea()
                        .setName("Primorskiy")
                        .setBuildings(Arrays.asList(
                                liveBuildingList.get(0),
                                liveBuildingList.get(1)
                        )),
                //  1
                new ResortArea()
                        .setName("City centre")
                        .setBuildings(Arrays.asList(
                                liveBuildingList.get(2),
                                liveBuildingList.get(3)
                        )),
                //  2
                new ResortArea()
                        .setName("Azure coast")
                        .setBuildings(Arrays.asList(
                                liveBuildingList.get(4),
                                liveBuildingList.get(5)
                        )),
                //  3
                new ResortArea()
                        .setName("Odessa")
                        .setBuildings(Arrays.asList(
                                liveBuildingList.get(6),
                                liveBuildingList.get(7)
                        )),

                //  ************    Great Britain    *************
                //  4
                new ResortArea()
                        .setName("Kemden")
                        .setBuildings(Arrays.asList(
                                liveBuildingList.get(8),
                                liveBuildingList.get(9)
                        )),
                //  5
                new ResortArea()
                        .setName("Lambet")
                        .setBuildings(Arrays.asList(
                                liveBuildingList.get(10),
                                liveBuildingList.get(11)
                        )),
                //  6
                new ResortArea()
                        .setName("Westminster")
                        .setBuildings(Arrays.asList(
                                liveBuildingList.get(12),
                                liveBuildingList.get(13)
                        )),
                //  7
                new ResortArea()
                        .setName("Islington")
                        .setBuildings(Arrays.asList(
                                liveBuildingList.get(14),
                                liveBuildingList.get(15)
                        )),

                //  ************    USA    *************
                //  8
                new ResortArea()
                        .setName("Celebration")
                        .setBuildings(Arrays.asList(
                                liveBuildingList.get(16),
                                liveBuildingList.get(17)
                        )),
                //  9
                new ResortArea()
                        .setName("Saut-Lake-Taho")
                        .setBuildings(Arrays.asList(
                                liveBuildingList.get(18),
                                liveBuildingList.get(19)
                        )),
                //  10
                new ResortArea()
                        .setName("Coronado")
                        .setBuildings(Arrays.asList(
                                liveBuildingList.get(20),
                                liveBuildingList.get(21)
                        )),
                //  11
                new ResortArea()
                        .setName("City centre")
                        .setBuildings(Arrays.asList(
                                liveBuildingList.get(22),
                                liveBuildingList.get(23)
                        ))
        ));

        // Add needed Area to internal Building
        resortAreaList.forEach(value -> {
            for (LiveBuilding liveBuilding: value.getBuildings()) {
                liveBuilding.setArea(value);
            }
        });


        //  Take related data - LiveBuilding
//        liveBuildingList.get(0).setArea(resortAreaList.get(0));
//        liveBuildingList.get(1).setArea(resortAreaList.get(0));
//        liveBuildingList.get(2).setArea(resortAreaList.get(1));
//        liveBuildingList.get(3).setArea(resortAreaList.get(1));
//        liveBuildingList.get(4).setArea(resortAreaList.get(2));
//        liveBuildingList.get(5).setArea(resortAreaList.get(2));
//        liveBuildingList.get(6).setArea(resortAreaList.get(3));
//        liveBuildingList.get(7).setArea(resortAreaList.get(3));
//        liveBuildingList.get(8).setArea(resortAreaList.get(4));
//        liveBuildingList.get(9).setArea(resortAreaList.get(4));
//        liveBuildingList.get(10).setArea(resortAreaList.get(5));
//        liveBuildingList.get(11).setArea(resortAreaList.get(5));
//        liveBuildingList.get(12).setArea(resortAreaList.get(6));
//        liveBuildingList.get(13).setArea(resortAreaList.get(6));
//        liveBuildingList.get(14).setArea(resortAreaList.get(7));
//        liveBuildingList.get(15).setArea(resortAreaList.get(7));
//        liveBuildingList.get(16).setArea(resortAreaList.get(8));
//        liveBuildingList.get(17).setArea(resortAreaList.get(8));
//        liveBuildingList.get(18).setArea(resortAreaList.get(9));
//        liveBuildingList.get(19).setArea(resortAreaList.get(9));
//        liveBuildingList.get(20).setArea(resortAreaList.get(10));
//        liveBuildingList.get(21).setArea(resortAreaList.get(10));
//        liveBuildingList.get(22).setArea(resortAreaList.get(11));
//        liveBuildingList.get(23).setArea(resortAreaList.get(11));


        //  ***     Creating CityList     ***
        List<ResortCity> cityList = new ArrayList<>();
        cityList.addAll(Arrays.asList(

                //  ***     Ukraine     ***
                //  0
                new ResortCity()
                        .setName("Odessa")
                        .setIsAbleForEntering(true)
                        .setAreas(Arrays.asList(
                                resortAreaList.get(0),
                                resortAreaList.get(1)
                        )),
                //  1
                new ResortCity()
                        .setName("Sevastopol")
                        .setIsAbleForEntering(false)
                        .setAreas(Arrays.asList(
                                resortAreaList.get(2),
                                resortAreaList.get(3)
                        )),

                //  ***     Great Britain     ***
                //  2
                new ResortCity()
                        .setName("London")
                        .setIsAbleForEntering(true)
                        .setAreas(Arrays.asList(
                                resortAreaList.get(4),
                                resortAreaList.get(5)
                        )),
                //  3
                new ResortCity()
                        .setName("Bristol")
                        .setIsAbleForEntering(false)
                        .setAreas(Arrays.asList(
                                resortAreaList.get(6),
                                resortAreaList.get(7)
                        )),

                //  ***     USA     ***
                //  4
                new ResortCity()
                        .setName("California")
                        .setIsAbleForEntering(true)
                        .setAreas(Arrays.asList(
                                resortAreaList.get(8),
                                resortAreaList.get(9)
                        )),
                //  5
                new ResortCity()
                        .setName("Los Angeles")
                        .setIsAbleForEntering(false)
                        .setAreas(Arrays.asList(
                                resortAreaList.get(10),
                                resortAreaList.get(11)
                        ))
        ));

        // Add needed City to internal Areas
        cityList.forEach(value -> {
            for (ResortArea resortArea: value.getAreas()) {
                resortArea.setCity(value);
            }
        });

        //  Set City to Area
//        resortAreaList.get(0).setCity(cityList.get(0));
//        resortAreaList.get(1).setCity(cityList.get(0));
//        resortAreaList.get(2).setCity(cityList.get(1));
//        resortAreaList.get(3).setCity(cityList.get(1));
//        resortAreaList.get(4).setCity(cityList.get(2));
//        resortAreaList.get(5).setCity(cityList.get(2));
//        resortAreaList.get(6).setCity(cityList.get(3));
//        resortAreaList.get(7).setCity(cityList.get(3));
//        resortAreaList.get(8).setCity(cityList.get(4));
//        resortAreaList.get(9).setCity(cityList.get(4));
//        resortAreaList.get(10).setCity(cityList.get(5));
//        resortAreaList.get(11).setCity(cityList.get(5));

        //  *****       Set Entrance Type       *****
        //  Odessa
        cityList.get(0).setEnterTypes(
                Arrays.asList(
                        entranceTypeList.get(0),
                        entranceTypeList.get(1),
                        entranceTypeList.get(3)
                )
        );
        //  Sevastopol
        cityList.get(1).setEnterTypes(
                Arrays.asList(
                        entranceTypeList.get(0),
                        entranceTypeList.get(1),
                        entranceTypeList.get(2),
                        entranceTypeList.get(3)
                )
        );
        //  London
        cityList.get(2).setEnterTypes(
                Arrays.asList(
                        entranceTypeList.get(0),
                        entranceTypeList.get(1),
                        entranceTypeList.get(3)
                )
        );
        //  Bristol
        cityList.get(3).setEnterTypes(
                Arrays.asList(
                        entranceTypeList.get(0),
                        entranceTypeList.get(1),
                        entranceTypeList.get(3)
                )
        );
        //  California
        cityList.get(4).setEnterTypes(
                Arrays.asList(
                        entranceTypeList.get(0),
                        entranceTypeList.get(1),
                        entranceTypeList.get(2),
                        entranceTypeList.get(3)
                )
        );
        //  Los Angeles
        cityList.get(5).setEnterTypes(
                Arrays.asList(
                        entranceTypeList.get(0),
                        entranceTypeList.get(1),
                        entranceTypeList.get(2),
                        entranceTypeList.get(3)
                )
        );


        //  *****       Set Traveling Type       *****
        //  Odessa
        cityList.get(0).setTravelingTypes(
                Arrays.asList(
                        travelingTypeList.get(0),
                        travelingTypeList.get(1),
                        travelingTypeList.get(2)
                )
        );
        //  Sevastopol
        cityList.get(1).setTravelingTypes(
                Arrays.asList(
                        travelingTypeList.get(0),
                        travelingTypeList.get(1),
                        travelingTypeList.get(2)
                )
        );
        //  London
        cityList.get(2).setTravelingTypes(
                Arrays.asList(
                        travelingTypeList.get(0),
                        travelingTypeList.get(1),
                        travelingTypeList.get(2)
                )
        );
        //  Bristol
        cityList.get(3).setTravelingTypes(
                Arrays.asList(
                        travelingTypeList.get(0),
                        travelingTypeList.get(1),
                        travelingTypeList.get(2)
                )
        );
        //  California
        cityList.get(4).setTravelingTypes(
                Arrays.asList(
                        travelingTypeList.get(0),
                        travelingTypeList.get(1),
                        travelingTypeList.get(2)
                )
        );
        //  Los Angeles
        cityList.get(5).setTravelingTypes(
                Arrays.asList(
                        travelingTypeList.get(0),
                        travelingTypeList.get(1),
                        travelingTypeList.get(2)
                )
        );

//        liveBuildingRepository.saveAll(liveBuildingList);
//        areaRepository.saveAll(resortAreaList);
//        entranceTypeRepository.saveAll(entranceTypeList);
//        travelingTypeRepository.saveAll(travelingTypeList);

        return cityList;
    }

    private List<Country> addToCountryCities() {
        //  ResortCities
        List<ResortCity> cityList = addToCityAreasAndLiveBuildings();
        //  EntranceTypes
        List<EntranceType> entranceTypeList = addEntranceTypes();

        List<Country> countryList = new ArrayList<>();
        countryList.addAll(Arrays.asList(

                //  0 - Ukraine
                new Country()
                        .setAbleForEntering(true)
                        .setName("Ukraine")
                        .setCities(Arrays.asList(
                                cityList.get(0),
                                cityList.get(1)
                        )),

                //  1 - Great Britain
                new Country()
                        .setAbleForEntering(true)
                        .setName("Great Britain")
                        .setCities(Arrays.asList(
                                cityList.get(2),
                                cityList.get(3)
                        )),

                //  2 - USA
                new Country()
                        .setAbleForEntering(true)
                        .setName("USA")
                        .setCities(Arrays.asList(
                                cityList.get(4),
                                cityList.get(5)
                        ))
        ));

        // Add needed Country to internal Cities
        countryList.forEach(value -> {
            for (ResortCity resortCity: value.getCities()) {
                resortCity.setCountry(value);
            }
        });

        //  ******  Set EntranceType  ******
        //  Ukraine
        countryList.get(0).setEnterTypes(
                Arrays.asList(
                        entranceTypeList.get(0),
                        entranceTypeList.get(1),
                        entranceTypeList.get(2),
                        entranceTypeList.get(3)
                )
        );

        //  Great Britain
        countryList.get(1).setEnterTypes(
                Arrays.asList(
                        entranceTypeList.get(0),
                        entranceTypeList.get(1),
                        entranceTypeList.get(2),
                        entranceTypeList.get(3)
                )
        );

        //  USA
        countryList.get(2).setEnterTypes(
                Arrays.asList(
                        entranceTypeList.get(0),
                        entranceTypeList.get(1),
                        entranceTypeList.get(2),
                        entranceTypeList.get(3)
                )
        );

        //  *****   Set Country to City  *****
//        cityList.get(0).setCountry(countryList.get(0));
//        cityList.get(1).setCountry(countryList.get(0));
//        cityList.get(2).setCountry(countryList.get(1));
//        cityList.get(3).setCountry(countryList.get(1));
//        cityList.get(4).setCountry(countryList.get(2));
//        cityList.get(5).setCountry(countryList.get(2));

//        cityRepository.saveAll(cityList);
//        entranceTypeRepository.saveAll(entranceTypeList);

        return countryRepository.saveAll(countryList);
    }

}
