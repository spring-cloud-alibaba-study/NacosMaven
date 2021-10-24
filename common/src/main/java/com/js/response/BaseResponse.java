package com.js.response;

import com.js.enums.StatusCode;
import com.js.intefaces.ErrorTypeException;
import lombok.Data;

@Data
public class BaseResponse<T> {

    /**
     * 返回编码 {@link com.js.enums.StatusCode}
     **/
    private String code;

    /**
     * 异常描述
     **/
    private String message;

    /**
     * 对应返回数据可以为空,某些场景可以设置空
     **/
    private T data;

    private ErrorTypeException exception;

    public BaseResponse() {

    }

    public BaseResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(String code, String message, T data, ErrorTypeException exception) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.exception = exception;
    }

    public static <T> BaseResponse<T> buildSuccess() {
        return build(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), null);
    }

    public static <T> BaseResponse<T> buildSuccess(String message, T data) {
        return build(StatusCode.SUCCESS.getCode(), message, data);
    }

    public static <T> BaseResponse<T> buildSuccess(T data) {
        return build(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), data);
    }

    public static <T> BaseResponse<T> buildFail() {
        return build(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), null);
    }

    public static <T> BaseResponse<T> buildFail(String message) {
        return build(StatusCode.FAIL.getCode(), message, null);
    }

    public static <T> BaseResponse<T> buildFail(ErrorTypeException exceptionEnum) {
        return build(StatusCode.FAIL.getCode(), exceptionEnum.getDescribe(), null, exceptionEnum);
    }

    public static <T> BaseResponse<T> buildFail(ErrorTypeException exceptionEnum, String message) {
        return build(StatusCode.FAIL.getCode(), message, null, exceptionEnum);
    }

    public static <T> BaseResponse<T> build(ErrorTypeException exceptionEnum, T data) {
        return build(StatusCode.FAIL.getCode(), exceptionEnum.getDescribe(), data);
    }

    public static <T> BaseResponse<T> build(String code, String message, T data, ErrorTypeException exceptionEnum) {
        return new BaseResponse<>(code, message, data, exceptionEnum);
    }

    public static <T> BaseResponse<T> build(String code, String message, T data) {
        return new BaseResponse<>(code, message, data);
    }
}
