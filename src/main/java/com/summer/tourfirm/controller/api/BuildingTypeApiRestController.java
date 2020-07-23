package com.summer.tourfirm.controller.api;

import com.summer.tourfirm.dto.BuildingTypeDTO;
import com.summer.tourfirm.dto.edit.BuildingTypeEditDTO;
import com.summer.tourfirm.service.IBuildingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/buildingtype")
public class BuildingTypeApiRestController {

    @Autowired
    IBuildingTypeService typeService;

    @GetMapping("/list")
    public ResponseEntity<List<BuildingTypeDTO>> getAll() {
        return new ResponseEntity<>(typeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<BuildingTypeDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(typeService.get(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<BuildingTypeDTO> create(@RequestBody @Valid BuildingTypeEditDTO buildingTypeEditDTO) {
        return new ResponseEntity<>(typeService.create(buildingTypeEditDTO), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<BuildingTypeDTO> update(@RequestBody @Valid BuildingTypeEditDTO buildingTypeEditDTO) {
        return new ResponseEntity<>(typeService.update(buildingTypeEditDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        typeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
