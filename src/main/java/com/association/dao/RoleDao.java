package com.association.dao;

import com.association.model.Role;
import com.association.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author baozi
 * @version 1.0
 * @date 2020/4/3 22:49
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

   /**用户角色权限*/
   @Query("from Role where roleId =:roleId")
   List<Role> selectRolePermissionByUserId(@Param("roleId") Long roleId);

   /**用户角色列表*/
   @Query("from Role")
   List<Role> selectRoleAll();

   /**
    * 根据用户ID获取角色选择框列表根据用户ID获取角色选择框列表
    * @param userId
    * @return
    */
   @Query("select roleName from Role")
   List<Integer> selectRoleListByUserId(Long userId);


}




