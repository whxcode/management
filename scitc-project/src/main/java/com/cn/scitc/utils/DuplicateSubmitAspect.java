package com.cn.scitc.utils;
import com.cn.scitc.exception.BusinessException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @description 防止表单重复提交拦截器
 */
@Aspect
@Component

public class DuplicateSubmitAspect {
    @Autowired
    private ObjectMapper objectMapper;


    public static final String  DUPLICATE_TOKEN_KEY="duplicate_token_key";
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Pointcut("execution(public * com.cn.scitc.web.controller..*(..))")

    public void webLog() {
    }

    @Before("webLog() && @annotation(token)")
    public void before(final JoinPoint joinPoint, DuplicateSubmitToken token ) throws BusinessException, IOException {
        if (token!=null){
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            HttpServletResponse response = attributes.getResponse();

            boolean isSaveSession=token.save();
            if (isSaveSession){
                String key = getDuplicateTokenKey(joinPoint);
                Object t = request.getSession().getAttribute(key);
                if (null==t){
                    String uuid= UUID.randomUUID().toString();
                    request.getSession().setAttribute(key.toString(),uuid);
                    logger.info("token-key="+key);
                    logger.info("token-value="+uuid.toString());
                }else {

                    response.sendRedirect("/message");
                    return;

                }


            }

        }
    }

    /**
     * 获取重复提交key
     * @param joinPoint
     * @return
     */
    public String getDuplicateTokenKey(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        StringBuilder key=new StringBuilder(DUPLICATE_TOKEN_KEY);
        key.append(",").append(methodName);
        return key.toString();
    }

    @AfterReturning("webLog() && @annotation(token)")
    public void doAfterReturning(JoinPoint joinPoint,DuplicateSubmitToken token) {
        // 处理完请求，返回内容
        logger.info("出方法：");
        if (token!=null){
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            boolean isSaveSession=token.save();
            if (isSaveSession){
                String key = getDuplicateTokenKey(joinPoint);
                Object t = request.getSession().getAttribute(key);
                if (null!=t&&token.type()==DuplicateSubmitToken.REQUEST){
                    request.getSession(false).removeAttribute(key);
                }
            }
        }
    }

    /**
     * 异常
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "webLog()&& @annotation(token)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e, DuplicateSubmitToken token) {
        if (null!=token
                && e instanceof BusinessException==false){
            //处理重复提交本身之外的异常
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            boolean isSaveSession=token.save();
            //获得方法名称
            if (isSaveSession){
                String key=getDuplicateTokenKey(joinPoint);
                Object t = request.getSession().getAttribute(key);
                if (null!=t){
                    //方法执行完毕移除请求重复标记
                    request.getSession(false).removeAttribute(key);
                    logger.info("异常情况--移除标记！");
                }
            }
        }
    }
}