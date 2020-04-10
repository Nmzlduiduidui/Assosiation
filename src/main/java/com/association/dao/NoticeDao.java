package com.association.dao;

import com.association.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author baozi
 * @version 1.0
 * @date 2020/4/10 18:14
 */
@Repository
public interface NoticeDao extends JpaRepository<Notice,Long> {

    /**
     * 查询公告信息
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Query("select n from Notice n where n.noticeId=:noticeId")
     Notice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     * @param notice 公告信息
     * @return 公告集合
     */
    @Query("select n from Notice n")
     List<Notice> selectNoticeList(Notice notice);


}
