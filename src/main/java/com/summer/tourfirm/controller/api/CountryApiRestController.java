package com.summer.tourfirm.controller.api;

import com.summer.tourfirm.dto.CountryDTO;
import com.summer.tourfirm.dto.edit.CountryEditDTO;
import com.summer.tourfirm.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryApiRestController {

    @Autowired
    ICountryService countryService;

    @GetMapping("/list")
    public ResponseEntity<List<CountryDTO>> getAll() {
        return new ResponseEntity<>(countryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<CountryDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(countryService.get(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CountryDTO> create(@RequestBody @Valid CountryEditDTO countryEditDTO) {
        return new ResponseEntity<>(countryService.create(countryEditDTO), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<CountryDTO> updateCountry(@RequestBody @Valid CountryEditDTO countryEditDTO) {
        return new ResponseEntity<>(countryService.update(countryEditDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") Long id) {
        countryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*-----EntranceType Rest-----*/

    @PostMapping("/{id}/enter-type/add")
    public ResponseEntity<CountryDTO> updateEntranceType(@PathVariable("id") Long id, @RequestBody List<Long> entranceTypeIds) {
        return new ResponseEntity<>(countryService.addEntranceType(id, entranceTypeIds), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/enter-type/delete/{typeId}")
    public ResponseEntity<Void> deleteEntranceType(@PathVariable("id") Long id, @PathVariable("typeId") Long typeId) {
        countryService.deleteEntranceType(id, typeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
