package com.surjo.admin.controller;

import com.surjo.admin.entity.VideoType;
import com.surjo.admin.model.Video;
import com.surjo.admin.model.Vote;
import com.surjo.admin.service.VideoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/video",produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoController {

   private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }
    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveVideo(@Valid @RequestBody Video video) {
        return ResponseEntity.ok(videoService.createVideo(video));
    }

    @GetMapping
    public ResponseEntity getAllVideo() {
        List<Video> videos = videoService.getAllVideo();
        return ResponseEntity.ok(videos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getVideoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(videoService.getVideoById(id));
    }

    @GetMapping(path = "/type/{type}")
    public ResponseEntity getVideoByType(@PathVariable("type") VideoType videoType) {
        return ResponseEntity.ok(videoService.getAllVideoByType(videoType));
    }

    @DeleteMapping
    public ResponseEntity deleteVote(@RequestParam(name="id",required = true)Long id) {
        videoService.deleteVideo(id);
        return ResponseEntity.accepted().build();
    }
}
