package com.summer.tourfirm.controller.api;

import com.summer.tourfirm.dto.EntranceTypeDTO;
import com.summer.tourfirm.dto.edit.EntranceTypeEditDTO;
import com.summer.tourfirm.service.IEntranceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/entrancetype")
public class EntranceTypeApiRestController {

    @Autowired
    IEntranceTypeService typeService;

    @GetMapping("/list")
    public ResponseEntity<List<EntranceTypeDTO>> getAll() {
        return new ResponseEntity<>(typeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<EntranceTypeDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(typeService.get(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<EntranceTypeDTO> create(@RequestBody @Valid EntranceTypeEditDTO typeEditDTO) {
        return new ResponseEntity<>(typeService.create(typeEditDTO), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<EntranceTypeDTO> update(@RequestBody @Valid EntranceTypeEditDTO typeEditDTO) {
        return new ResponseEntity<>(typeService.update(typeEditDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        typeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
