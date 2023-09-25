package com.zh.infrastructure.exception;

import com.zh.common.http.HttpResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    public HttpResult handler(RuntimeException e){
        e.printStackTrace();
        return HttpResult.error(e.getMessage());
    }
}
