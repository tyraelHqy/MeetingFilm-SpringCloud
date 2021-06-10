package com.mooc.meetingfilm.cinema.controller.vo;

import com.mooc.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import lombok.Data;

@Data
public class DescribeCinemasRespVO {
    private String brandId;
    private String areaId;
    private String hallTypeIds;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTele;
    private String cinemaImgAddress;
    private String cinemaPrice;

    public static DescribeCinemasRespVO pojo2VO(MoocCinemaT cinemaT){
        DescribeCinemasRespVO describeCinemasRespVO = new DescribeCinemasRespVO();
        describeCinemasRespVO.setBrandId(cinemaT.getBrandId()+"");
        describeCinemasRespVO.setAreaId(cinemaT.getAreaId()+"");
        describeCinemasRespVO.setHallTypeIds(cinemaT.getHallIds());
        describeCinemasRespVO.setCinemaName(cinemaT.getCinemaName());
        describeCinemasRespVO.setCinemaAddress(cinemaT.getCinemaAddress());
        describeCinemasRespVO.setCinemaTele(cinemaT.getCinemaPhone());
        describeCinemasRespVO.setCinemaImgAddress(cinemaT.getImgAddress());
        describeCinemasRespVO.setCinemaPrice(cinemaT.getMinimumPrice()+"");


        return describeCinemasRespVO;
    }
}
