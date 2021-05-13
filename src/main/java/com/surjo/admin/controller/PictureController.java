package com.surjo.admin.controller;

import com.surjo.admin.model.Cart;
import com.surjo.admin.model.Picture;
import com.surjo.admin.service.PictureService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/picture",produces = MediaType.APPLICATION_JSON_VALUE)
public class PictureController {

    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePicture(@Valid @RequestBody Picture picture) {
        return ResponseEntity.ok(pictureService.createPicture(picture));
    }

    @GetMapping
    public ResponseEntity getAllPicture() {
        List<Picture> pictureList = pictureService.getAllPictureList();
        return ResponseEntity.ok(pictureList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getPictureById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(pictureService.getPictureById(id));
    }

    @DeleteMapping
    public ResponseEntity deletePicture(@RequestParam(name="id",required = true)Long id) {
        pictureService.deletePicture(id);
        return ResponseEntity.accepted().build();
    }
}
