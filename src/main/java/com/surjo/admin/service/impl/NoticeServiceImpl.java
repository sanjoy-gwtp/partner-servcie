package com.surjo.admin.service.impl;

import com.surjo.admin.entity.NoticeEntity;
import com.surjo.admin.model.Notice;
import com.surjo.admin.repository.NoticeRepository;
import com.surjo.admin.service.NoticeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final ModelMapper modelMapper;

    public NoticeServiceImpl(NoticeRepository noticeRepository, ModelMapper modelMapper) {
        this.noticeRepository = noticeRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Notice createNotice(Notice notice) {
        NoticeEntity noticeEntity=noticeRepository.save(modelMapper.map(notice,NoticeEntity.class));
        return modelMapper.map(noticeEntity,Notice.class);
    }

    @Override
    public List<Notice> getAllNotice() {
        return noticeRepository.findAll()
                .stream().map(
                        noticeEntity ->
                                modelMapper.map(noticeEntity,Notice.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNotice(Long id) {
        noticeRepository.delete(noticeRepository.getOne(id));
    }

    @Override
    public Notice getNoticeById(Long id) {
        return modelMapper.map(noticeRepository.getOne(id),Notice.class);
    }
}
