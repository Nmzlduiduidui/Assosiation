package com.association.intercepter;

import com.association.annotation.PassToken;
import com.association.annotation.UserLoginToken;
import com.association.model.User;
import com.association.service.Impl.UserServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
/*
    @Autowired
    UserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object objece) throws Exception {

        //从http取出方法头
        String token = httpServletRequest.getHeader("token");

        //如果不是映射到方法，直接通过
        if (!(objece instanceof HandlerMethod)) {

            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) objece;

        Method method = handlerMethod.getMethod();

        //检查是否有PassToken注释 ，有就跳过
        if (method.isAnnotationPresent(PassToken.class)) {

            //method.getAnnotation(PassToken.class)如果方法存在名为PassToken.class的注释，
            // 则返回指定类型的元素的注释，否则为null
            PassToken passToken = method.getAnnotation(PassToken.class);

            if (passToken.required()) {

                return true;

            }
        }

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {

            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);

            if (userLoginToken.required()) {

                //执行认证
                if (token == null) {
                    throw new RuntimeException("token不存在,请重新登录");
                }

                //获取token中的userId 判断用户是否存在
                //用户id类型为Long，token中
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }

                Long userid = Long.valueOf(userId);
                User user = userService.findUserById(userid);

                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }

                //验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();

                try {
                    jwtVerifier.verify(token);
                }catch (JWTVerificationException j){
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }*/
}