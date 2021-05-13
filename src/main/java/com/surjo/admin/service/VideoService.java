package com.surjo.admin.service;

import com.surjo.admin.entity.VideoType;
import com.surjo.admin.model.Video;
import com.surjo.admin.model.Vote;

import java.util.List;

public interface VideoService {

    Video createVideo(Video video);

    List<Video> getAllVideo();

    List<Video> getAllVideoByType(VideoType videoType);

    Video getVideoById(Long id);

    void deleteVideo(Long id);
}
