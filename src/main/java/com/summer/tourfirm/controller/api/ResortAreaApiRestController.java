package com.summer.tourfirm.controller.api;

import com.summer.tourfirm.dto.ResortAreaDTO;
import com.summer.tourfirm.dto.edit.ResortAreaEditDTO;
import com.summer.tourfirm.service.IResortAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/resortarea")
public class ResortAreaApiRestController {

    @Autowired
    IResortAreaService resortAreaService;

    @GetMapping("/list")
    public ResponseEntity<List<ResortAreaDTO>> getAll() {
        return new ResponseEntity<>(resortAreaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ResortAreaDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(resortAreaService.get(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ResortAreaDTO> create(@RequestBody @Valid ResortAreaEditDTO resortAreaEditDTO) {
        return new ResponseEntity<>(resortAreaService.create(resortAreaEditDTO), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<ResortAreaDTO> update(@RequestBody @Valid ResortAreaEditDTO resortAreaEditDTO) {
        return new ResponseEntity<>(resortAreaService.update(resortAreaEditDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        resortAreaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
