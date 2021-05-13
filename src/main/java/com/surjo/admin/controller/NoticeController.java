package com.surjo.admin.controller;

import com.surjo.admin.model.Notice;
import com.surjo.admin.model.Type;
import com.surjo.admin.service.NoticeService;
import com.surjo.admin.service.TypeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/notice",produces = MediaType.APPLICATION_JSON_VALUE)
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }


    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveNotice(@Valid @RequestBody Notice notice) {
        return ResponseEntity.ok(noticeService.createNotice(notice));
    }

    @GetMapping
    public ResponseEntity getAllNotice() {
        List<Notice> notices = noticeService.getAllNotice();
        return ResponseEntity.ok(notices);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getNoticeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(noticeService.getNoticeById(id));
    }

    @DeleteMapping
    public ResponseEntity deleteNotice(@RequestParam(name="id",required = true)Long id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.accepted().build();
    }
}
