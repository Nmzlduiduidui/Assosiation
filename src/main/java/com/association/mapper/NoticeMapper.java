package com.association.mapper;

import com.association.model.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 通知公告表 数据层
 * 
 * @author ruoyi
 */
@Mapper
public interface NoticeMapper
{
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public Notice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<Notice> selectNoticeList(Notice notice);

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(Notice notice);

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(Notice notice);

    /**
     * 批量删除公告
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    public int deleteNoticeById(Long noticeId);

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] noticeIds);
}