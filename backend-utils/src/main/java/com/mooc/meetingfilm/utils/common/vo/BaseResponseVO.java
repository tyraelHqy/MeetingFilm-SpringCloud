package com.mooc.meetingfilm.utils.common.vo;

import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * 表现层公共返回
 */

@Data
public class BaseResponseVO<M> {
    // 业务编号
    private Integer code;

    // 异常信息
    private String message;

    // 业务数据返回
    private M data;

    private BaseResponseVO(){}

    // 成功无参数
    public static BaseResponseVO success(){
        BaseResponseVO responseVO = new BaseResponseVO();
        responseVO.setCode(200);
        responseVO.setMessage("");
        return responseVO;
    }

    // 成功有参数
    public static<M> BaseResponseVO success(M data){
        BaseResponseVO responseVO = new BaseResponseVO();
        responseVO.setCode(200);
        responseVO.setMessage("");
        responseVO.setData(data);
        return responseVO;
    }

    // 出现异常
    public static<M> BaseResponseVO serviceException(CommonServiceException e){
        BaseResponseVO responseVO = new BaseResponseVO();
        responseVO.setCode(e.getCode());
        responseVO.setMessage(e.getMessage());
        return responseVO;
    }
}
