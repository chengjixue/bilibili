package edu.xcj.bilibili.service.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import edu.xcj.bilibili.domain.exception.ConditionException;

import java.util.Calendar;
import java.util.Date;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/24 16:00
 */
public class TokenUtil {
    private static final String ISSUER = "xuecj";

    /*
     * 创建token
     * */

    public static String generateToken(Long userId) throws Exception {
        Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        //设置过期时间
        calendar.add(Calendar.SECOND, 30);
        return JWT.create().withKeyId(String.valueOf(userId))
                .withIssuer(ISSUER)
                .withExpiresAt(calendar.getTime())
                .sign(algorithm);
    }

    /*
     *
     * 验证token
     * */

    public static Long verifyToken(String token)  {
        try {
            Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String userId = jwt.getKeyId();
            return Long.valueOf(userId);
        }catch (TokenExpiredException e){
            throw new ConditionException("555","token过期！");
        }catch (Exception e) {
            throw new ConditionException("非法用户token！");
        }


    }


}
