package com.association.model;

import com.association.web.domain.BaseEntity;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 部门表 dept
 *
 */
@Entity
@Data
@Table(name = "dept")
public class Dept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long deptId;

    /** 父部门ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 显示顺序 */
    private String orderNum;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 部门状态:0正常,1停用 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 父部门名称 */
    private String parentName;

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
    
    /** 子部门 */
    @ElementCollection(targetClass = Dept.class)
    private List<Dept> children = new ArrayList<Dept>();

}
