package edu.xcj.edu.xcj.bilibili.api.support;

import edu.xcj.bilibili.domain.exception.ConditionException;
import edu.xcj.bilibili.service.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/24 16:43
 */
@Component
public class UserSupport {
    public Long getCurrentUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = requestAttributes.getRequest().getHeader("token");
        Long userId = TokenUtil.verifyToken(token);
        if (userId < 0) {
            throw new ConditionException("非法用户");

        }
        return userId;

    }


}
