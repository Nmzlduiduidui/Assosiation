package com.association.model;

import com.association.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
@Table(name="user")
public class User extends BaseEntity {

    /** 用户ID */
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long userId;

    /** 角色组 */
    private Long deptId;

    /** 用户昵称 */
    private String nickName;

    /** 用户名 */
    private String userName;

    /** 用户密码*/
    private String password;

    /** 学号 */
    private String studentId;

    /** 生日 */
    private String birthday;

    /** 学院 */
    private String major;

    /** 手机*/
    private String phone;

    /** 邮箱*/
    private String email;

    /** QQ */
    private String QQ;

    /** 性别 1:男 0:女 */
    private String sex;

    /** 年龄 */
    private String grade;

    /**爱好 */
    private String hobby;

    /** 技能 */
    private String skill;



    /** 状态 1:停用 0:正常*/
    private String status;

    /** 类型*/
    private Integer type;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

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



    //region 一对多
/*
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Blog> blogs = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Position> positions = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Activity> activities = new ArrayList<>();


    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JsonIgnore
    private List<Association> associations = new ArrayList<>();
*/
//endregion

    public User(Long userId)
    {
        this.userId = userId;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    public User() {
    }
}
