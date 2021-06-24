package com.mooc.meetingfilm.utils.exception;

import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(CommonServiceException.class)
    @ResponseBody
    public BaseResponseVO ServiceExceptionHandler(HttpServletRequest request,CommonServiceException e){
        log.error("CommonServiceException, code:{}, message:{}",e.getCode(),e.getMessage());

        return BaseResponseVO.serviceException(e);
    }
}
