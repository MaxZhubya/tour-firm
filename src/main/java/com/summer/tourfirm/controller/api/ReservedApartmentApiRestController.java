package com.summer.tourfirm.controller.api;

import com.summer.tourfirm.dto.ReservedApartmentDTO;
import com.summer.tourfirm.dto.edit.ReservedApartmentEditDTO;
import com.summer.tourfirm.service.IReservedApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reservedapartment")
public class ReservedApartmentApiRestController {

    @Autowired
    IReservedApartmentService reservedApartmentService;

    @GetMapping("/list")
    public ResponseEntity<List<ReservedApartmentDTO>> getAll() {
        return new ResponseEntity<>(reservedApartmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ReservedApartmentDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(reservedApartmentService.get(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ReservedApartmentDTO> create(@RequestBody @Valid ReservedApartmentEditDTO reservedApartmentEditDTO) {
        return new ResponseEntity<>(reservedApartmentService.create(reservedApartmentEditDTO), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<ReservedApartmentDTO> update(@RequestBody @Valid ReservedApartmentEditDTO reservedApartmentEditDTO) {
        return new ResponseEntity<>(reservedApartmentService.update(reservedApartmentEditDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        reservedApartmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
