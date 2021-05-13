package com.surjo.admin.controller;

import com.surjo.admin.model.Type;
import com.surjo.admin.model.Vote;
import com.surjo.admin.service.VoteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/vote",produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

   private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveVote(@Valid @RequestBody Vote vote) {
        return ResponseEntity.ok(voteService.createVote(vote));
    }

    @GetMapping
    public ResponseEntity getAllVote() {
        List<Vote> votes = voteService.getAllVote();
        return ResponseEntity.ok(votes);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getVoteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(voteService.getVoteById(id));
    }

    @DeleteMapping
    public ResponseEntity deleteVote(@RequestParam(name="id",required = true)Long id) {
        voteService.deleteVote(id);
        return ResponseEntity.accepted().build();
    }
}
