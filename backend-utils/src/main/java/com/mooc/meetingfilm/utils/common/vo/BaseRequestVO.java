package com.mooc.meetingfilm.utils.common.vo;

import com.mooc.meetingfilm.utils.exception.CommonServiceException;

public abstract class BaseRequestVO {

    /**
     * 公共的参数验证方法
     */
    public abstract void checkParam() throws CommonServiceException;
}
