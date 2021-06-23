package com.mooc.meetingfilm.hall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.mooc.meetingfilm.hall.apis.FilmFeignApi;
import com.mooc.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mooc.meetingfilm.hall.dao.entity.MoocFieldT;
import com.mooc.meetingfilm.hall.dao.entity.MoocHallFilmInfoT;
import com.mooc.meetingfilm.hall.dao.mapper.MoocFieldTMapper;
import com.mooc.meetingfilm.hall.dao.mapper.MoocHallFilmInfoTMapper;
import com.mooc.meetingfilm.hall.service.HallServiceAPI;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.tools.Tool;

@Service
public class HallServiceImpl implements HallServiceAPI {

    @Resource
    private MoocFieldTMapper fieldTMapper;

    @Resource
    private MoocHallFilmInfoTMapper hallFilmInfoTMapper;

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient eurekaClient;

    @Autowired
    private FilmFeignApi filmFeignApi;

    /**
     * 获取全部播放厅接口
     * @return
     * @throws CommonServiceException
     */
    @Override
    public IPage<HallsRespVO> describeHalls(HallsReqVO reqVO) throws CommonServiceException {
        Page<HallsReqVO> page = new Page<>(reqVO.getNowPage(),reqVO.getPageSize());

        QueryWrapper queryWrapper = new QueryWrapper();
        if(ToolUtils.strIsNotNull(reqVO.getCinemaId())){
            queryWrapper.eq("cinema_id",reqVO.getCinemaId());
        }
        IPage<HallsRespVO> result = fieldTMapper.describeHalls(page, queryWrapper);
        return result;
    }

    @Override
    public void saveHall(HallSavedReqVO reqVO) throws CommonServiceException {

        // 播放厅的列表数据
        MoocFieldT field = new MoocFieldT();

        MoocHallFilmInfoT hallFilmInfoT = new MoocHallFilmInfoT();
        field.setCinemaId(ToolUtils.str2Int(reqVO.getCinemaId()));
        field.setFilmId(ToolUtils.str2Int(reqVO.getFilmId()));
        field.setBeginTime(reqVO.getBeginTime());
        field.setEndTime(reqVO.getEndTime());
        field.setHallId(ToolUtils.str2Int(reqVO.getHallTypeId()));
        field.setHallName(reqVO.getHallName());
        field.setPrice(ToolUtils.str2Int(reqVO.getFilmPrice()));

        fieldTMapper.insert(field);

        // 播放厅对应的影片数据,影片冗余数据，缓存中有一份
        MoocHallFilmInfoT hallFilmInfo = describeFilmInfo(reqVO.getFilmId());

        hallFilmInfoTMapper.insert(hallFilmInfo);


    }

    // 播放厅对应的影片数据， 影片冗余数据， 缓存里有一份
    private MoocHallFilmInfoT describeFilmInfo(String filmId) throws CommonServiceException{

        BaseResponseVO<DescribeFilmRespVO> baseResponseVO = filmFeignApi.describeFilmById(filmId);

        DescribeFilmRespVO filmResult = baseResponseVO.getData();
        if(filmResult == null || ToolUtils.strIsNull(filmResult.getFilmId())){
            throw new CommonServiceException(404,"抱歉，未能找到对应的电影信息，filmId：" +filmId);
        }

        // 组织参数
        MoocHallFilmInfoT hallFilmInfo = new MoocHallFilmInfoT();

//        "filmId":"1",
//        "filmName":"我不是药神",
//        "filmLength":"132",
//        "filmCats":"喜剧,剧情",
//        "actors":"程勇,曹斌,吕受益,刘思慧",
//        "imgAddress":"films/238e2dc36beae55a71cabfc14069fe78236351.jpg",

        hallFilmInfo.setFilmId(ToolUtils.str2Int(filmResult.getFilmId()));
        hallFilmInfo.setFilmName(filmResult.getFilmName());
        hallFilmInfo.setFilmLength(filmResult.getFilmLength());
        hallFilmInfo.setFilmCats(filmResult.getFilmCats());
        hallFilmInfo.setActors(filmResult.getActors());
        hallFilmInfo.setImgAddress(filmResult.getImgAddress());

        return hallFilmInfo;
    }

//
//    // 播放厅对应的影片数据， 影片冗余数据， 缓存里有一份
//    private MoocHallFilmInfoT describeFilmInfo(String filmId) throws CommonServiceException{
//        // GET REGISTER
//        ServiceInstance choose = eurekaClient.choose("film-service");
//        // 组织调用参数
//        String hostname = choose.getHost();
//        int port = choose.getPort();
//
//        String uri = "/films/"+filmId;
//
//        String url = "http://film-service:"+port + uri;
//
//        // 通过restTemplate调用影片服务
//        JSONObject baseResponseVO = restTemplate.getForObject(url, JSONObject.class);
//
//        // 解析返回值
//        JSONObject dataJson = baseResponseVO.getJSONObject("data");
//
//        // 组织参数
//        MoocHallFilmInfoT hallFilmInfo = new MoocHallFilmInfoT();
//
////        "filmId":"1",
////        "filmName":"我不是药神",
////        "filmLength":"132",
////        "filmCats":"喜剧,剧情",
////        "actors":"程勇,曹斌,吕受益,刘思慧",
////        "imgAddress":"films/238e2dc36beae55a71cabfc14069fe78236351.jpg",
//
//        hallFilmInfo.setFilmId(dataJson.getIntValue("filmId"));
//        hallFilmInfo.setFilmName(dataJson.getString("filmName"));
//        hallFilmInfo.setFilmLength(dataJson.getString("filmLength"));
//        hallFilmInfo.setFilmCats(dataJson.getString("filmCats"));
//        hallFilmInfo.setActors(dataJson.getString("actors"));
//        hallFilmInfo.setImgAddress(dataJson.getString("imgAddress"));
//
//        return hallFilmInfo;
//    }
}
