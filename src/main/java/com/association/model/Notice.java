package com.association.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "notice")
public class Notice {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long noticeId;

    /** 公告标题 */
    private String noticeTitle;

    /** 公告内容 */
    private String noticeContent;

    /** 公告类型（1通知 2公告） */
    private String noticeType;

    /** 公告图片链接 */
    private String pictureUrl;

    /** 公告状态（0正常 1关闭） */
    private String status;
    /** 备注*/
    private String remark;

    /** 创建者 */
    private String createBy;

    /** 更新者 */
    private String updateBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;        //创建时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;        //更新时间

}
