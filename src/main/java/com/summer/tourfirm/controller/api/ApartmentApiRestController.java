package com.summer.tourfirm.controller.api;

import com.summer.tourfirm.dto.ApartmentDTO;
import com.summer.tourfirm.dto.edit.ApartmentEditDTO;
import com.summer.tourfirm.service.IApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/apartment")
public class ApartmentApiRestController {

    @Autowired
    IApartmentService apartmentService;

    @GetMapping("/list")
    public ResponseEntity<List<ApartmentDTO>> getAll() {
        return new ResponseEntity<>(apartmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ApartmentDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(apartmentService.get(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApartmentDTO> create(@RequestBody @Valid ApartmentEditDTO apartmentEditDTO) {
        return new ResponseEntity<>(apartmentService.create(apartmentEditDTO), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<ApartmentDTO> update(@RequestBody @Valid ApartmentEditDTO apartmentEditDTO) {
        return new ResponseEntity<>(apartmentService.update(apartmentEditDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        apartmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
