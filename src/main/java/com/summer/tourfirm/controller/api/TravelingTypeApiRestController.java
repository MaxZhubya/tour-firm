package com.summer.tourfirm.controller.api;

import com.summer.tourfirm.dto.TravelingTypeDTO;
import com.summer.tourfirm.dto.edit.TravelingTypeEditDTO;
import com.summer.tourfirm.service.ITravelingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/travelingtype")
public class TravelingTypeApiRestController {

    @Autowired
    ITravelingTypeService typeService;

    @GetMapping("/list")
    public ResponseEntity<List<TravelingTypeDTO>> getAll() {
        return new ResponseEntity<>(typeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<TravelingTypeDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(typeService.get(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TravelingTypeDTO> create(@RequestBody @Valid TravelingTypeEditDTO typeEditDTO) {
        return new ResponseEntity<>(typeService.create(typeEditDTO), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<TravelingTypeDTO> update(@RequestBody @Valid TravelingTypeEditDTO typeEditDTO) {
        return new ResponseEntity<>(typeService.update(typeEditDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        typeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
