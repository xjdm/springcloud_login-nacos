package com.idstaa.exception;

import com.netflix.client.http.HttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjie
 * @date 2021/1/5 0:56
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(IdstaaException.class)
    public Map<String, Object> handleCustomException(IdstaaException customException) {
        Map<String, Object> errorResultMap = new HashMap<>(16);
        errorResultMap.put("code", customException.getCode());
        errorResultMap.put("message", customException.getMessage());
        return errorResultMap;
    }
}
