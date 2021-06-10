package com.mooc.meetingfilm.cinema.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.mooc.meetingfilm.cinema.controller.vo.CinemaSavedReqVO;
import com.mooc.meetingfilm.cinema.controller.vo.DescribeCinemasRespVO;
import com.mooc.meetingfilm.cinema.service.CinemaServiceAPI;
import com.mooc.meetingfilm.utils.common.vo.BasePageVO;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    private CinemaServiceAPI cinemaServiceAPI;

    @RequestMapping(value = "/cinema:add", method = RequestMethod.POST)
    public BaseResponseVO saveCinema(@RequestBody CinemaSavedReqVO cinemaSavedReqVO) throws CommonServiceException, CommonServiceException {

        // 数据验证
        cinemaSavedReqVO.checkParam();

        cinemaServiceAPI.saveCinema(cinemaSavedReqVO);

        return BaseResponseVO.success();
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public BaseResponseVO describeCinemas(BasePageVO basePageVO) throws CommonServiceException {

        IPage<DescribeCinemasRespVO> results = cinemaServiceAPI.describeCinemas(basePageVO.getNowPage(), basePageVO.getPageSize());

        if (results.getSize() == 0){
            return BaseResponseVO.success();
        }

        if (basePageVO.getNowPage() > 10000) {
            throw new CommonServiceException(400, "nowPage太大了，不支持此处理");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        Map<String, Object> cinemas = describePageResult(results, "cinema");

        return BaseResponseVO.success(cinemas);
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
