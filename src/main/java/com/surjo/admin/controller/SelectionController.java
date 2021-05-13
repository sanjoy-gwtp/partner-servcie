package com.surjo.admin.controller;

import com.surjo.admin.model.Selection;
import com.surjo.admin.model.Type;
import com.surjo.admin.service.SelectionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/selection",produces = MediaType.APPLICATION_JSON_VALUE)
public class SelectionController {

    private final SelectionService selectionService;

    public SelectionController(SelectionService selectionService) {
        this.selectionService = selectionService;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveSelection(@Valid @RequestBody Selection selection) {
        return ResponseEntity.ok(selectionService.createSelection(selection));
    }

    @GetMapping
    public ResponseEntity getAllSelection() {
        List<Selection> selections = selectionService.getAllSelection();
        return ResponseEntity.ok(selections);
    }

    @GetMapping(path = "/user")
    public ResponseEntity getAllSelectionByUser() {
        List<Selection> selections = selectionService.getAllSelectionByUser();
        return ResponseEntity.ok(selections);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getSelectionById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(selectionService.getSelectionById(id));
    }

    @GetMapping(path = "/voteId/{voteId}")
    public ResponseEntity getSelectionByVoteId(@PathVariable("voteId") Long voteId) {
        return ResponseEntity.ok(selectionService.getSelectionByVoteId(voteId));
    }

    @DeleteMapping
    public ResponseEntity deleteType(@RequestParam(name="id",required = true)Long id) {
        selectionService.deleteSelection(id);
        return ResponseEntity.accepted().build();
    }
}
