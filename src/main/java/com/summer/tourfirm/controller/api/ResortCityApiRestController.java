package com.summer.tourfirm.controller.api;

import com.summer.tourfirm.dto.ResortCityDTO;
import com.summer.tourfirm.dto.edit.ResortCityEditDTO;
import com.summer.tourfirm.service.IResortCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/resortcity")
public class ResortCityApiRestController {

    @Autowired
    IResortCityService resortCityService;

    @GetMapping("/list")
    public ResponseEntity<List<ResortCityDTO>> getAll() {
        return new ResponseEntity<>(resortCityService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ResortCityDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(resortCityService.get(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ResortCityDTO> create(@RequestBody @Valid ResortCityEditDTO resortCityEditDTO) {
        return new ResponseEntity<>(resortCityService.create(resortCityEditDTO), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<ResortCityDTO> updateResortCity(@RequestBody @Valid ResortCityEditDTO resortCityEditDTO) {
        return new ResponseEntity<>(resortCityService.update(resortCityEditDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResortCity(@PathVariable("id") Long id) {
        resortCityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*-----EntranceType Rest-----*/

    @PostMapping("/{id}/enter-type/add")
    public ResponseEntity<ResortCityDTO> updateEntranceType(@PathVariable("id") Long id, @RequestBody List<Long> entranceTypeIds) {
        return new ResponseEntity<>(resortCityService.addEntranceType(id, entranceTypeIds), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/enter-type/delete/{typeId}")
    public ResponseEntity<Void> deleteEntranceType(@PathVariable("id") Long id, @PathVariable("typeId") Long typeId) {
        resortCityService.deleteEntranceType(id, typeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*-----TravelingType Rest-----*/

    @PostMapping("/{id}/traveling-type/add")
    public ResponseEntity<ResortCityDTO> updateTravelingType(@PathVariable("id") Long id, @RequestBody List<Long> travelingTypeIds) {
        return new ResponseEntity<>(resortCityService.addTravelingType(id, travelingTypeIds), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/traveling-type/delete/{typeId}")
    public ResponseEntity<Void> deleteTravelingType(@PathVariable("id") Long id, @PathVariable("typeId") Long typeId) {
        resortCityService.deleteTravelingType(id, typeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
