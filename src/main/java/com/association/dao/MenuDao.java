package com.association.dao;

import com.association.model.Menu;
import com.association.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author baozi
 * @version 1.0
 * @date 2020/4/3 22:49
 */
@Repository
public interface MenuDao extends JpaRepository<Menu, Long> {


    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    //@Query("select m from Menu m ")
    //public List<Menu> selectMenuList(Menu menu);

    /**
     * 根据用户所有权限
     *
     * @return 权限列表
     */
    //public List<String> selectMenuPerms();

    /**
     * 根据用户查询系统菜单列表
     * @param menu 菜单信息
     * @return 菜单列表
     */
    //public List<Menu> selectMenuListByUserId(Menu menu);

    /**
     * 根据用户ID查询权限
     * @param userId 用户ID
     * @return 权限列表
     */
   // @Query("select m from Menu m where m.userId =:userId")
   // List<String> selectMenuPermsByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询菜单
     * @return 菜单列表
     */
   // @Query("select m from Menu m")
    //List<Menu> selectMenuTreeAll();

    /**
     * 根据用户ID查询菜单
     * @return 菜单列表
     */
   // List<Menu> selectMenuTreeByUserId(Long userId);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
   // @Query("select m from Menu m where m.roleId=:roleId")
  //  List<Integer> selectMenuListByRoleId(Long roleId);

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Query("select m from Menu m where m.menuId=:menuId")
    Menu selectMenuByMenuId(Long menuId);

/**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果
     *//*

    int hasChildByMenuId(Long menuId);
*/

    /**
     * 新增菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
//    int insertMenu(Menu menu);

    /**
     * 修改菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
  //  int updateMenu(Menu menu);

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Query("delete from Menu m where m.menuId=:menuId")
    int deleteMenuByMenuId(Long menuId);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menuName 菜单名称
     * @param parentId 父菜单ID
     * @return 结果
     */
    @Query("select m from Menu m where m.menuName=:menuName and m.parentId=:parentId")
    Menu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Long parentId);
}





