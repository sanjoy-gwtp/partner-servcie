package com.surjo.admin.service;

import com.surjo.admin.model.Category;
import com.surjo.admin.model.Notice;

import java.util.List;

public interface NoticeService {

    Notice createNotice(Notice notice);

    List<Notice> getAllNotice();

    void deleteNotice(Long id);

    Notice getNoticeById(Long id);
}
