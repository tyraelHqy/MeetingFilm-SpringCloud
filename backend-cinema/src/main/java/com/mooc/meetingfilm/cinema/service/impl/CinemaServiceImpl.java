package com.mooc.meetingfilm.cinema.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.cinema.controller.vo.CinemaSavedReqVO;
import com.mooc.meetingfilm.cinema.controller.vo.DescribeCinemasRespVO;
import com.mooc.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.mooc.meetingfilm.cinema.dao.mapper.MoocCinemaTMapper;
import com.mooc.meetingfilm.cinema.service.CinemaServiceAPI;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements CinemaServiceAPI {

    @Autowired
    private MoocCinemaTMapper cinemaTMapper;

    @Override
    public void saveCinema(CinemaSavedReqVO reqVO) throws CommonServiceException {
        MoocCinemaT cinema = new MoocCinemaT();

        cinema.setCinemaName(reqVO.getCinemaName());
        cinema.setCinemaPhone(reqVO.getCinemaTele());
        cinema.setBrandId(ToolUtils.str2Int(reqVO.getBrandId()));
        cinema.setAreaId(ToolUtils.str2Int(reqVO.getAreaId()));
        cinema.setHallIds(reqVO.getHallTypeIds());
        cinema.setImgAddress(reqVO.getCinemaImgAddress());
        cinema.setCinemaAddress(reqVO.getCinemaAddress());
        cinema.setMinimumPrice(ToolUtils.str2Int(reqVO.getCinemaPrice()));

        cinemaTMapper.insert(cinema);
    }

    @Override
    public IPage<DescribeCinemasRespVO> describeCinemas(int nowPage, int pageSize) throws CommonServiceException {
        Page<MoocCinemaT> page = new Page<>(nowPage, pageSize);
        IPage<MoocCinemaT> cinemaPage = cinemaTMapper.selectPage(page, null);
        return cinemaPage.convert(
                entity -> {
                    return DescribeCinemasRespVO.pojo2VO(entity);
                }
        );
    }
}
