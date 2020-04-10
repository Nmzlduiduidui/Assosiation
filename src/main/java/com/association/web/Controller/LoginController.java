package com.association.web.Controller;

import com.association.common.ServletUtils;
import com.association.constant.Constants;
import com.association.model.User;
import com.association.result.Result;
import com.association.security.LoginUser;
import com.association.security.service.LoginService;
import com.association.security.service.PermissionService;
import com.association.security.service.TokenService;
import com.association.service.AuthenticationService;
import com.association.service.Impl.UserServiceImpl;
import com.association.util.TokenUtil;
import com.association.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author baozi
 * @version 1.0
 * @date 2020/3/25 0025 10:03
 */

/**
 * 登录控制器，前后端分离用的不同协议和端口，所以需要加入@CrossOrigin支持跨域。
 */

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    TokenService tokenService;

    /**
     * 用户登录
     * @return AjaxResult
     */
    @PostMapping("/api/login")
     public AjaxResult login(String username, String password, String code, String uuid){

        /*
        User userForBase = new User();
        userForBase.setUserName(userService.findByUserName(username).getUserName());
        userForBase.setPassword(userService.findByPassword(MD5Util.convertMD5(MD5Util.convertMD5(password))).getPassword());
       */

        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(username, password, code, uuid);

        //String token = authenticationService.getToken(user);

        ajax.put(Constants.TOKEN, token);

        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @RequestMapping(value = "/api/getInfo", method = RequestMethod.GET)
    public Result getInfo() {

        Result result = new Result();

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        User user = loginUser.getUser();

        //角色集合
        Set<String> roles = permissionService.getRolePermission(user);

        return result;
    }

}
