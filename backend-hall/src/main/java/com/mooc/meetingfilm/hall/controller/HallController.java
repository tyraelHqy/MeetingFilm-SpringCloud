package com.mooc.meetingfilm.hall.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.mooc.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mooc.meetingfilm.hall.service.HallServiceAPI;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/halls")
public class HallController {

    @Autowired
    private HallServiceAPI hallServiceAPI;

    /**
     * 获取播放厅接口
     * @param hallsReqVO
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public BaseResponseVO describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException {

        hallsReqVO.checkParam();
        IPage<HallsRespVO> page = hallServiceAPI.describeHalls(hallsReqVO);

        Map<String, Object> halls = describePageResult(page, "halls");
        return BaseResponseVO.success(halls);
    }

    /**
     * @Description: 新增播放厅
     * @Param: [hallsReqVO]
     * @return: com.mooc.meetingfilm.utils.common.vo.BaseResponseVO
     * @Author: tyrael
     */
    @RequestMapping(value = "/hall:add",method = RequestMethod.POST)
    public BaseResponseVO saveHall(@RequestBody HallSavedReqVO hallSavedReqVO) throws CommonServiceException {

        hallSavedReqVO.checkParam();

        hallServiceAPI.saveHall(hallSavedReqVO);

        return BaseResponseVO.success();
    }

    /**
     * 获取分页对象的公共接口
     *
     * @param page
     * @param title
     * @return
     */
    private Map<String, Object> describePageResult(IPage page, String title) {
        Map<String, Object> result = Maps.newHashMap();

        result.put("totalSize", page.getTotal());
        result.put("totalPages", page.getPages());
        result.put("pageSize", page.getSize());
        result.put("nowPage", page.getCurrent());
        result.put(title, page.getRecords());

        return result;
    }
}
