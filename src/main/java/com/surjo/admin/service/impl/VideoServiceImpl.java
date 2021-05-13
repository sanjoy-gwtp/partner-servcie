package com.surjo.admin.service.impl;

import com.surjo.admin.entity.VideoEntity;
import com.surjo.admin.entity.VideoType;
import com.surjo.admin.model.Video;
import com.surjo.admin.repository.VideoRepository;
import com.surjo.admin.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;

    public VideoServiceImpl(VideoRepository videoRepository, ModelMapper modelMapper) {
        this.videoRepository = videoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Video createVideo(Video video) {
        VideoEntity videoEntity=videoRepository.save(modelMapper.map(video,VideoEntity.class));
        return modelMapper.map(videoEntity,Video.class);
    }

    @Override
    public List<Video> getAllVideo() {
        return videoRepository.findAll().stream().map(
                videoEntity -> modelMapper.map(videoEntity,Video.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Video> getAllVideoByType(VideoType videoType) {
        return videoRepository.findVideoEntitiesByVideoTypeOrderByIdDesc(videoType).stream().map(
                videoEntity -> modelMapper.map(videoEntity,Video.class))
                .collect(Collectors.toList());
    }

    @Override
    public Video getVideoById(Long id) {
        return modelMapper.map(videoRepository.getOne(id),Video.class);
    }

    @Override
    public void deleteVideo(Long id) {
        videoRepository.delete(videoRepository.getOne(id));
    }
}
