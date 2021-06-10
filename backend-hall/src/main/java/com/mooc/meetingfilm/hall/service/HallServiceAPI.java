package com.mooc.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;

public interface HallServiceAPI {

    IPage<HallsRespVO> describeHalls(HallsReqVO reqVO) throws CommonServiceException;

    void saveHall(HallSavedReqVO hallSavedReqVO) throws CommonServiceException;
}
