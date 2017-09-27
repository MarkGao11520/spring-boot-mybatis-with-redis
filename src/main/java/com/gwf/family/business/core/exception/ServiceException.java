package com.gwf.family.business.core.exception;

import com.gwf.family.business.core.results.ResultEnum;

/**
 * 服务（业务）异常如“ 账号或密码错误 ”，该异常只做INFO级别的日志记录 @see WebMvcConfigurer
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 3729706443523052156L;

    public ServiceException() {
    }

    public ServiceException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
