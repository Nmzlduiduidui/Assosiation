package com.association.dao;

import com.association.model.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author baozi
 * @version 1.0
 * @date 2020/4/8 9:19
 */
public interface UserPostDao extends JpaRepository<UserPost,Long> {
    /**
     * 通过userId删除用户与岗位的关联
     * @param userId
     * @return
     */
    @Modifying
    @Transactional
    @Query("delete from UserPost p where p.userId in (?1)")
    int deleteByUserPostUserId(Long userId);

}
