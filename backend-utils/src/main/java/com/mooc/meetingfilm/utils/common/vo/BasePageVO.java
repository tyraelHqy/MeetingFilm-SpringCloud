package com.mooc.meetingfilm.utils.common.vo;

import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import lombok.Data;

@Data
public class BasePageVO extends BaseRequestVO{
    private Integer nowPage=1;

    private Integer pageSize=10;


    @Override
    public void checkParam() throws CommonServiceException {
        if (nowPage.equals(0) || pageSize.equals(0)){
            throw new CommonServiceException(404,"page or size must be required");
        }
    }
}
