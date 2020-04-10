package com.association.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "message")
public class Message {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long messageId;

    private String title;
    private String content;
    private String pictureUrl;
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


/*    @ManyToOne(fetch = FetchType.LAZY)
    private User user;*/

}
