package com.association.web.Controller;

import com.association.model.Notice;
import com.association.service.NoticeService;
import com.association.web.common.BaseController;
import com.association.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author baozi
 * @version 1.0
 * @date 2020/4/10 18:06
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 获取通知公告列表
     * @param notice
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo list(Notice notice){
        startPage();
        List<Notice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

}
