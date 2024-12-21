package com.songshilong.framework.starter.idempotent.core.token;

import cn.hutool.core.util.StrUtil;
import com.songshilong.framework.starter.cache.DistributedCache;
import com.songshilong.framework.starter.convention.errorcode.BaseErrorCode;
import com.songshilong.framework.starter.convention.exception.ClientException;
import com.songshilong.framework.starter.idempotent.config.IdempotentProperties;
import com.songshilong.framework.starter.idempotent.core.AbstractIdempotentExecuteHandler;
import com.songshilong.framework.starter.idempotent.core.IdempotentParamWrapper;
import com.songshilong.framework.starter.idempotent.core.RepeatConsumptionException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.core.token
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  20:40
 * @Description: IdempotentTokenExecuteHandler
 * @Version: 1.0
 */
@RequiredArgsConstructor
public class IdempotentTokenExecuteHandler extends AbstractIdempotentExecuteHandler implements IdempotentTokenService {

    private final DistributedCache distributedCache;
    private final IdempotentProperties idempotentProperties;
    private static final String TOKEN = "token";
    private static final String CACHE_TOKEN_PREFIX_KEY = "idempotent:token:";
    private static final Long TOKEN_EXPIRED_TIME = 6000L;


    @Override
    protected IdempotentParamWrapper buildWrapper(ProceedingJoinPoint joinPoint) {
        return new IdempotentParamWrapper();
    }

    @Override
    public void handler(IdempotentParamWrapper wrapper) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        String token = StrUtil.EMPTY;
        if (Objects.nonNull(servletRequestAttributes)) {
            request = servletRequestAttributes.getRequest();
            token = request.getHeader(TOKEN);
            if (StrUtil.isEmpty(token)) {
                token = request.getParameter(token);
                if (StrUtil.isEmpty(token)) {
                    throw new ClientException(BaseErrorCode.IDEMPOTENT_TOKEN_NULL_ERROR);
                }
            }
        }
        Boolean delFlag = distributedCache.delete(token);
        if (delFlag) {
            return;
        }
        String errMsg = StrUtil.isNotBlank(wrapper.getIdempotent().message())
                ? wrapper.getIdempotent().message()
                : BaseErrorCode.IDEMPOTENT_TOKEN_DELETE_ERROR.message();
        throw new ClientException(errMsg, BaseErrorCode.IDEMPOTENT_TOKEN_DELETE_ERROR);
    }

    @Override
    public String createToken() {
        String token = Optional.ofNullable(idempotentProperties.getPrefix()).orElse(CACHE_TOKEN_PREFIX_KEY) + UUID.randomUUID();
        distributedCache.put(token, StrUtil.EMPTY, Optional.ofNullable(idempotentProperties.getTimeout()).orElse(TOKEN_EXPIRED_TIME));
        return token;
    }
}
