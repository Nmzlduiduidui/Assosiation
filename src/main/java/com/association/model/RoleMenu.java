package com.association.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

/**
 * 角色和菜单关联 sys_role_menu
 * 
 * @author baozi
 */
@Entity
@Data
@Table(name="roleMenu")
public class RoleMenu
{

    /** 角色ID */
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long roleId;
    
    /** 菜单ID */
    private Long menuId;


}
