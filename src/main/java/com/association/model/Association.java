package com.association.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "association")
public class Association {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long associationId;

    private String name;
    private String introduce;
    private String activityPlace;
    private String state;
    private String stateID;
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


/*    @ManyToMany(mappedBy = "associations")
    private List<User> users = new ArrayList<>();

    @OneToOne
    private  Teacher teacher;

    @OneToMany(mappedBy = "association",fetch = FetchType.EAGER)
    private List<Activity> activities = new ArrayList<>();

    @OneToMany(mappedBy = "association",fetch = FetchType.EAGER)
    private List<Notice> notices = new ArrayList<>();*/


}
