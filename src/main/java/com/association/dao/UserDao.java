package com.association.dao;

import com.association.model.Role;
import com.association.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author baozi
 * @version 1.0
 * @date 2020/4/3 22:49
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 通过用户名查询用户
     *
     * @param userName
     * @return
     */
    @Query("select u from User u where u.userName =:userName")
    User findByUserName(@Param("userName") String userName);

    /**
     * 通过用户名查询用户
     *
     * @param userId
     * @return
     */
    @Query("select u from User u where u.userId =:userId")
    User findByUserId(@Param("userId") Long userId);


    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Query("select u from User u where u.userId =:userId")
    User selectUserById(@Param("userId") Long userId);

    /**
     * 校验用户名是否唯一
     *
     * @param userName
     * @return
     */
//    @Query("select count() from User where userName =:userName")
    @Query("select u from User u where u.userName =:userName")
    User checkUserNameUnique(@Param("userName") String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phone
     * @return
     */
    @Query("select u from User u  where u.phone =:phone")
    User checkPhoneUnique(@Param("phone") String phone);


    /**
     * 校验Email是否唯一
     *
     * @param email
     * @return
     */
    @Query(" select u from User u where u.phone =:email")
    User checkEmailUnique(@Param("email") String email);

    /**
     * 用户列表
     *
     * @param user
     * @return
     */
    @Query("select u from User u ")
    List<User> selectUserList(User user);


    /**
     * 更新和删除需要加事务,并且加上@Modify注解
     * 通过用户userId，批量删除用户
     *
     * @param ids
     * @return
     */
    @Modifying
    @Transactional
    @Query("delete from User u where u.userId in (?1)")
    int deleteBatch(List<Long> ids);

    //region @Query方法修改用户（不使用）
    /**
     * 修改用户
     * @param user
     * @return
     */
   /* @Modifying
    @Transactional
    @Query("update User u set u.userName=:#{#User.userName}," +
            "u.phone=:#{#User.phone}," +
            "u.studentId=:#{#User.studentId}," +
            "u.studentId=:#{#User.studentId}," +
            "u.major=:#{#User.major}" +
            " where u.userId=:#{#User.userId}")
    int updateUser(@Param("user")User user);*/
    //endregion

}




