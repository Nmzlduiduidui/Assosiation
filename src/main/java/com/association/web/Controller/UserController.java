package com.association.web.Controller;

import com.association.common.utils.StringUtils;
import com.association.constant.UserConstants;
import com.association.model.User;
import com.association.service.RoleService;
import com.association.service.UserService;
import com.association.web.common.BaseController;
import com.association.web.domain.AjaxResult;
import com.association.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author baozi
 * @version 1.0
 * @date 2020/4/1 10:56
 */
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    private RoleService roleService;

/*   @Autowired
    private PostService postService;*/

    /**
     * 根据用户编号获取详细信息
     */
    @GetMapping(value = { "/", "/{userId}" })
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId)
    {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("roles", roleService.selectRoleAll());
        //ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId))
        {
            ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
             //ajax.put("postIds", postService.selectPostListByUserId(userId));
            //ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }

    //region  //新增用户
    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public AjaxResult addUser(@Validated @RequestBody User user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
//      user.setCreateBy(SecurityUtils.getUsername());

        toAjax(userService.insertUser(user));
        return AjaxResult.success("操作成功");
    }
    //endregion

    //region //删除用户
    /**
     * 删除用户
     * @param userIds
     * @return
     */
    @DeleteMapping("/{userIds}")
    public AjaxResult deleteUser(@PathVariable Long[] userIds){
        return toAjax(userService.deleteUserByIds(userIds));
    }
    //endregion

    //region //修改用户
    @PutMapping
    public AjaxResult updateUser(@Validated @RequestBody User user){
        userService.checkUserAllowed(user);
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))){
            return AjaxResult.error("修改用户"+user.getUserName()+"失败，手机号码已经存在");
        }
        else if(UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))){
            return AjaxResult.error("修改用户"+user.getUserName()+"失败，邮箱账号已经存在");
        }
        return toAjax(userService.updateUser(user));
    }
    //endregion

    //region  //获取用户列表
    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public TableDataInfo listUser(User user)
    {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }
    //endregion



}
