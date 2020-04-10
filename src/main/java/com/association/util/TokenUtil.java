package com.association.util;

import com.auth0.jwt.JWT;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Token验证工具类
 *
 * @author baozi
 * @version 1.0
 * @date 2020/3/28 17:51
 */
public class TokenUtil {

    public static String getTokenUserId(){

        //从请求头中取出token
        String token = getRequest().getHeader("token");

        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();

        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

}
