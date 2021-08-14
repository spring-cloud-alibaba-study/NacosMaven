package com.js.advices;

import com.js.enums.ExceptionEnum;
import com.js.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class CommonExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResponse errorHandler() {
        return BaseResponse.buildFail(ExceptionEnum.NETWORH_ANOMALY_ERRORTYPE);
    }
}