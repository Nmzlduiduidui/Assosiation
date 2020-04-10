package com.association.dao;

import com.association.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.List;

/**
 * @author baozi
 * @version 1.0
 * @date 2020/4/8 9:35
 */
public interface UserRoleDao extends JpaRepository<UserRole,Long> {

    /**
     * 更新和删除需要加事务,并且加上@Modify注解
     * @param userId
     * @return
     */
    @Modifying
    @Transactional
    @Query("delete from UserRole u where u.userId in (?1)")
    int deleteByUserRoleUserId(@Param("userId") Long userId);

}
