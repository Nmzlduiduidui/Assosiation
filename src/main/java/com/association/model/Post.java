package com.association.model;

import com.association.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 岗位表 sys_post
 * 
 * @author baozi
 */
@Entity
@Data
@Table(name = "post")
public class Post extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    /** 岗位序号 */
     private Long postId;

    /** 岗位编码 */
    private String postCode;

    /** 岗位名称 */
     private String postName;

    /** 岗位排序 */
    private String postSort;

    /** 状态（0正常 1停用） */
     private String status;

    /** 用户是否存在此岗位标识 默认不存在 */
    private boolean flag = false;


}
