package com.summer.tourfirm.controller.api;

import com.summer.tourfirm.dto.FilterDTO;
import com.summer.tourfirm.dto.LiveBuildingDTO;
import com.summer.tourfirm.dto.edit.LiveBuildingEditDTO;
import com.summer.tourfirm.service.ILiveBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/livebuilding")
public class LiveBuildingApiRestController {

    @Autowired
    ILiveBuildingService liveBuildingService;

    @GetMapping("/list")
    public ResponseEntity<List<LiveBuildingDTO>> getAll() {
        return new ResponseEntity<>(liveBuildingService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<LiveBuildingDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(liveBuildingService.get(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<LiveBuildingDTO> create(@RequestBody @Valid LiveBuildingEditDTO liveBuildingEditDTO) {
        return new ResponseEntity<>(liveBuildingService.create(liveBuildingEditDTO), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<LiveBuildingDTO> update(@RequestBody @Valid LiveBuildingEditDTO liveBuildingEditDTO) {
        return new ResponseEntity<>(liveBuildingService.update(liveBuildingEditDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        liveBuildingService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<LiveBuildingDTO>> getAllByFilter(@RequestBody @Valid FilterDTO filterDTO) {
        return new ResponseEntity<>(liveBuildingService.getAllByFilter(filterDTO), HttpStatus.OK);
    }
}
