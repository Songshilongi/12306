package com.songshilong.framework.starter.user.toolkit;

import com.alibaba.fastjson2.JSON;
import com.songshilong.framework.starter.base.constant.UserConstant;
import com.songshilong.framework.starter.user.core.UserInfoDTO;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.user.toolkit
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  19:51
 * @Description: JwtUtil
 * @Version: 1.0
 */
@Slf4j
public class JwtUtil {

    /**
     * jwt令牌的过期时间间隔
     */
    private static final long EXPIRATION = 86400L;
    /**
     * jwt生成的Token的前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";
    /**
     * jwt令牌的颁发者
     */
    public static final String ISS = "index12306";
    /**
     * jwt令牌的秘钥
     */
    public static final String SECRET = "SecretKey039245678901232039487623456783092349288901402967890140939827";


    /**
     * 根据用户信息生成 JwtToken
     *
     * @param userInfoDTO {@link UserInfoDTO}
     * @return token
     */
    public static String generateAccessToken(UserInfoDTO userInfoDTO) {
        Map<String, Object> customerUserMap = new HashMap<>();
        customerUserMap.put(UserConstant.USER_ID_KEY, userInfoDTO.getUserId());
        customerUserMap.put(UserConstant.USER_NAME_KEY, userInfoDTO.getUsername());
        customerUserMap.put(UserConstant.REAL_NAME_KEY, userInfoDTO.getRealName());
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuedAt(new Date())
                .setIssuer(ISS)
                .setSubject(JSON.toJSONString(customerUserMap))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .compact();
        return TOKEN_PREFIX + token;
    }


    /**
     * 解析token获取用户信息
     * @param token jwtToken
     * @return {@link UserInfoDTO}
     */
    public static UserInfoDTO parseJwtToken(String token) {
        try {
            if (StringUtils.hasText(token)) {
                String actualToken = token.replace(TOKEN_PREFIX, "");
                Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJwt(actualToken).getBody();
                Date expiration = claims.getExpiration();
                if (expiration.after(new Date())) {
                    String subject = claims.getSubject();
                    return JSON.parseObject(subject, UserInfoDTO.class);
                }
            }
        } catch (ExpiredJwtException ignored){

        } catch (Exception e) {
            log.error("Jwt解析失败 token:{}", token);
        }
        return null;
    }
}
