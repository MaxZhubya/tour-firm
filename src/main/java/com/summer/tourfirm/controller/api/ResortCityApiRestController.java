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
    public ResponseEntity<ResortCityDTO> update(@RequestBody @Valid ResortCityEditDTO resortCityEditDTO) {
        return new ResponseEntity<>(resortCityService.update(resortCityEditDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        resortCityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
