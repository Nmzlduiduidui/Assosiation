package com.association.service.Impl;

import com.association.common.exception.CustomException;
import com.association.common.utils.SecurityUtils;
import com.association.common.utils.StringUtils;
import com.association.constant.UserConstants;
import com.association.dao.UserDao;
import com.association.dao.UserPostDao;
import com.association.dao.UserRoleDao;
import com.association.mapper.*;
import com.association.model.*;
import com.association.service.ConfigService;
import com.association.service.UserService;
import com.association.util.UpdateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户 业务层处理
 * 
 * @author baozi
 */
@Service
public class UserServiceImpl implements UserService
{
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserPostMapper userPostMapper;

    @Autowired
    private ConfigService configService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserPostDao userPostDao;

    @Autowired
    private UserRoleDao userRoleDao;

    //region 根据条件分页查询用户列表
    /**
     * 根据条件分页查询用户列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userDao.selectUserList(user);
    }
    //endregion

    //region 通过用户名查询用户
    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByUserName(String userName)
    {
        User user = userDao.findByUserName(userName);
        return user;
    }
    //endregion

    //region 通过用户ID查询用户
    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public User selectUserById(Long userId)
    {
        return userDao.selectUserById(userId);
    }
    //endregion

    //region 查询用户所属角色组
    /**
     * 查询用户所属角色组
     * 
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(String userName)
    {
        List<Role> list = roleMapper.selectRolesByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (Role role : list)
        {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }
    //endregion

    //region 查询用户所属岗位组
    /**
     * 查询用户所属岗位组
     * 
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(String userName)
    {
        List<Post> list = postMapper.selectPostsByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (Post post : list)
        {
            idsStr.append(post.getPostName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }
    //endregion

    //region 校验用户名称是否唯一
    /**
     * 校验用户名称是否唯一
     * 
     * @param userName 用户名称
     * @return 结果
     */
    @Override
    public String checkUserNameUnique(String userName)
    {
        User info = userDao.checkUserNameUnique(userName);
        if (StringUtils.isNotNull(info))
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
    //endregion

    //region 校验手机号是否唯一
    /**
     * 校验手机号是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkPhoneUnique(User user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        User info = userDao.checkPhoneUnique(user.getPhone());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
    //endregion

    //region 校验email是否唯一
    /**
     * 校验email是否唯一
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkEmailUnique(User user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        User info = userDao.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
    //endregion

    //region 校验用户是否允许操作
    /**
     * 校验用户是否允许操作
     * 
     * @param user 用户信息
     */
    public String checkUserAllowed(User user)
    {
        if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin())
        {
            return UserConstants.NOT_UNIQUE;
           //throw new CustomException("不允许操作超级管理员用户");
        }
        return UserConstants.UNIQUE;
    }
    //endregion

    //region 新增保存用户信息
    /**
     * 新增保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertUser(User user)
    {
        // 新增用户信息
        int rows=0;
        user.setCreateBy(user.getUserName()); //用户创建自
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword())); //用户密码
        user.setDelFlag("0"); //0代表存在 2代表删除
        user.setStatus("0");// 0:正常 1:停用
        Date date = new Date();
        user.setCreateTime(date); //添加用户时间
        user.setUpdateTime(date);//更新时间
        User addUser = userDao.save(user);

        if (StringUtils.isNotNull(addUser) && StringUtils.isNotNull(addUser.getUserId()))
        {
         rows= 1;
        }
      // 新增用户岗位关联
        insertUserPost(user);
        // 新增用户与角色管理
        insertUserRole(user);
        return rows;
    }
    //endregion

    //region 修改保存用户信息
    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUser(User user)
    {
        Long userId = user.getUserId();

        // 删除用户与角色关联
        userRoleDao.deleteByUserRoleUserId(userId);
        // 新增用户与角色管理
        insertUserRole(user);
        // 删除用户与岗位关联
        userPostDao.deleteByUserPostUserId(userId);
        // 新增用户与岗位管理
        insertUserPost(user);

        if (StringUtils.isNotNull(userId)) {
            //更新时间
            Date date = new Date();
            user.setUpdateTime(date);

            User oldUser = userDao.findByUserId(userId);
            if (StringUtils.isNotNull(oldUser)){

                //将前端传来的不为空参数(也即是要修改值)copy覆盖原始对象属性值，
                // 通过BeanUtils.copyNullProperties(Object source, Object target)
                UpdateUtil.copyNullProperties(user,oldUser);
            }
            userDao.save(user);
        }
        return 1;
    }
    //endregion

    //region 修改用户状态
    /**
     * 修改用户状态
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserStatus(User user)
    {
//        return userDao.updateUser(user);
        return 1;
    }
    //endregion

    //region 修改用户基本信息
    /**
     * 修改用户基本信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserProfile(User user)
    {
        return userMapper.updateUser(user);
    }
    //endregion

    //region 修改用户头像
    /**
     * 修改用户头像
     *
     * @param userName 用户ID
     * @param avatar 头像地址
     * @return 结果
     */
    public boolean updateUserAvatar(String userName, String avatar)
    {
        return userMapper.updateUserAvatar(userName, avatar) > 0;
    }
    //endregion

    //region 重置用户密码
    /**
     * 重置用户密码
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetPwd(User user)
    {
        return userMapper.updateUser(user);
    }
    //endregion

    //region 重置用户密码
    /**
     * 重置用户密码
     * 
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    @Override
    public int resetUserPwd(String userName, String password)
    {
        return userMapper.resetUserPwd(userName, password);
    }
    //endregion

    //region 新增用户角色信息
    /**
     * 新增用户角色信息
     * 
     * @param user 用户对象
     */
    public void insertUserRole(User user)
    {
        Long[] roles = user.getRoleIds();
        if (StringUtils.isNotNull(roles))
        {
            // 新增用户与角色管理
            List<UserRole> list = new ArrayList<UserRole>();
            for (Long roleId : roles)
            {
                UserRole ur = new UserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0)
            {
                userRoleDao.saveAll(list);
            }
        }
    }
    //endregion

    //region 新增用户岗位信息
    /**
     * 新增用户岗位信息
     * 
     * @param user 用户对象
     */
    public void insertUserPost(User user)
    {
        Long[] posts = user.getPostIds();
        if (StringUtils.isNotNull(posts))
        {
            // 新增用户与岗位管理
            List<UserPost> list = new ArrayList<UserPost>();
            for (Long postId : posts)
            {
                UserPost up = new UserPost();
                up.setUserId(user.getUserId());
                up.setPostId(postId);
                list.add(up);
            }
            if (list.size() > 0)
            {
                userPostDao.saveAll(list);
            }
        }
    }
    //endregion

    //region 通过用户ID删除用户
    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */

    @Override
    public int deleteUserById(Long userId)
    {
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 删除用户与岗位表
        userPostMapper.deleteUserPostByUserId(userId);
        return userMapper.deleteUserById(userId);
    }
    //endregion

    //region 批量删除用户信息
    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    public int deleteUserByIds(Long[] userIds)
    {
        //显示数组userIds的所有元素
        for (Long userId : userIds)
        {
            checkUserAllowed(new User(userId));
        }

        // 接收long[]类型的字符串数组，转换成string类型的字符串数组
        String[] strArr = new String[userIds.length];
        for(int i=0;i<strArr.length;i++){
            strArr[i] = String.valueOf(userIds[i]);
        }
        // 将字符串数组转为List<Long> 类型
        List<Long> LString = new ArrayList<Long>();
        for(String str : strArr){
            LString.add(new Long(str));
        }

        return userDao.deleteBatch(LString);
    }
    //endregion

    //region 导入用户数据
    /**
     * 导入用户数据
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importUser(List<User> userList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(userList) || userList.size() == 0)
        {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (User user : userList)
        {
            try
            {
                // 验证是否存在这个用户
                User u = userMapper.selectUserByUserName(user.getUserName());
                if (StringUtils.isNull(u))
                {
                    user.setPassword(SecurityUtils.encryptPassword(password));
                    user.setCreateBy(operName);
                    this.insertUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    user.setUpdateBy(operName);
                    this.updateUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getUserName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getUserName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
    //endregion

}
