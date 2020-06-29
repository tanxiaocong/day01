package com.vsc.website.common;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.ObjectError;

import java.util.List;

public class ResultObject<T> {
    @ApiModelProperty(value = "结果代码", required = true)
    private int code;
    @ApiModelProperty(value = "结果消息")
    private String message;
    private List<ObjectError> parameterErrors;
    T data;

    public ResultObject(MessageCode code,Integer language) {
        this.code = code.getCode();
        this.message = Util.getMessage(code.name(),language);
    }

    public ResultObject(MessageCode code,Integer language, T data, String... argv) {
        this.code = code.getCode();
        this.message = Util.getMessage(code.name(),language, argv);
        this.data = data;
    }

    public ResultObject(MessageCode code,Integer language, T data, List<ObjectError> parameterErrors) {
        this.code = code.getCode();
        this.message = Util.getMessage(code.name(),language);
        this.data = data;
        this.parameterErrors = parameterErrors;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ObjectError> getParameterErrors() {
        return parameterErrors;
    }

    public void setParameterErrors(List<ObjectError> parameterErrors) {
        this.parameterErrors = parameterErrors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
