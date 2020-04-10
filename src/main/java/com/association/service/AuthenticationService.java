package com.association.service;


import com.association.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationService {
    public String getToken(User user) {
        String token = "";

        //设定token有效时间
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);

        token = JWT.create()
                // 将 userid 保存到 token 里
                .withAudience(user.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));   // 以 password 作为 token 的密钥
        return token;

    }
}





