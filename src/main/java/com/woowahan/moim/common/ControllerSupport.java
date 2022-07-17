package com.woowahan.moim.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerSupport {

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public String handle(Exception e) {
        return e.getMessage();
    }
}
