package com.association.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户和角色关联 sys_user_role
 * 
 * @author baozi
 */
@Entity
@Data
@Table(name="userRole")
@IdClass(PrimaryKeyUserRole.class)
public class UserRole
{
    /** 用户ID */
    @Id
    private Long userId;

    /** 角色ID */
    @Id
    private Long roleId;

}
