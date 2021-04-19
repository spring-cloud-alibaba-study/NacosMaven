package com.js.advices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CommonExceptionAdvice {
    private Logger log = LoggerFactory.getLogger(CommonExceptionAdvice.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(HttpServletRequest request, Exception ex) {
        Map map = new HashMap();
        map.put("retrnCode", 100);
        map.put("errorMsg", ex.getMessage());
        return map;
    }
}