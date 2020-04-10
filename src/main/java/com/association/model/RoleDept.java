package com.association.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

/**
 * 角色和部门关联 sys_role_dept
 * 
 * @author baozi
 */

@Entity
@Data
@Table(name="roleDept")
public class RoleDept
{
    /** 角色ID */
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long roleId;
    
    /** 部门ID */
    private Long deptId;


}
