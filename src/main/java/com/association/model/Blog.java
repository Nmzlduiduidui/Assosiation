package com.association.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="blog")
public class Blog {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long blogId;

    private String title;           //标题
    private String content;         //内容
    private String firstPicture;    //首图
    private String flag;            //标记
    private Integer views;          //浏览次数
    private boolean appreciation;   //赞赏开启
    private boolean shareStatement; //版权开启
    private boolean commendable;   //评论开启
    private boolean published;      //是否发布
    private boolean recommend;      //是否推荐
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

/*
    @ManyToOne(fetch = FetchType.EAGER)
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "blog",fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();
*/

}
