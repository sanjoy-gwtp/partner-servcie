package com.surjo.admin.controller;

import com.surjo.admin.model.Cart;
import com.surjo.admin.model.Type;
import com.surjo.admin.service.TypeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/type",produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }


    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveType(@Valid @RequestBody Type type) {
        return ResponseEntity.ok(typeService.createType(type));
    }

    @GetMapping
    public ResponseEntity getAllType() {
        List<Type> typeList = typeService.getAllTypeList();
        return ResponseEntity.ok(typeList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getTypeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(typeService.getTypeById(id));
    }

    @DeleteMapping
    public ResponseEntity deleteType(@RequestParam(name="id",required = true)Long id) {
        typeService.deleteType(id);
        return ResponseEntity.accepted().build();
    }
}
