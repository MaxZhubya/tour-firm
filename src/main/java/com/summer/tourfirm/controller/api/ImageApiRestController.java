package com.summer.tourfirm.controller.api;

import com.summer.tourfirm.dto.ImageDTO;
import com.summer.tourfirm.dto.edit.ImageEditDTO;
import com.summer.tourfirm.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageApiRestController {

    @Autowired
    IImageService imageService;

    @GetMapping("/list")
    public ResponseEntity<List<ImageDTO>> getAll() {
        return new ResponseEntity<>(imageService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ImageDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(imageService.get(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ImageDTO> create(@RequestBody @Valid ImageEditDTO imageEditDTO) {
        return new ResponseEntity<>(imageService.create(imageEditDTO), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<ImageDTO> update(@RequestBody @Valid ImageEditDTO imageEditDTO) {
        return new ResponseEntity<>(imageService.update(imageEditDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        imageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
