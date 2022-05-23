package edu.xcj.bilibili.service.handler;

import edu.xcj.bilibili.domain.JsonResponse;
import edu.xcj.bilibili.domain.exception.ConditionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/23 21:22
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
//优先级最高
public class CommonGlobalExceptionHandler{
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResponse<String> commonExceptionHanler(HttpServletRequest request,Exception e){
        String errorMsg = e.getMessage();
        if (e instanceof ConditionException){
         String   errorCode=((ConditionException)e).getCode();
         return new JsonResponse<>(errorCode,errorMsg);
        }else {
            return new JsonResponse<>("500",errorMsg);
        }
    }


}
