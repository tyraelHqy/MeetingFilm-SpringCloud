package com.mooc.meetingfilm.testng.films;

import com.mooc.meetingfilm.testng.common.RestUtils;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class FilmsTest {

    @Test
    public void films() {
        String uri = "http://localhost:8401/films";
        RestTemplate restTemplate = RestUtils.getRestTemplate();
        String response = restTemplate.getForObject(uri, String.class);

        log.info("response: {}",response);
    }

    @Test
    public void addFilm(){
        String url = "http://localhost:8401/films/film:add";
        FilmSavedReqVO filmSavedReqVO = new FilmSavedReqVO();
        filmSavedReqVO.setFilmStatus("1");
        filmSavedReqVO.setFilmName("变形金刚海盗");
        filmSavedReqVO.setFilmEnName("Transformers");
        filmSavedReqVO.setMainImgAddress("img/1.jpa");
        filmSavedReqVO.setFilmScore("10");
        filmSavedReqVO.setFilmScorers("123456");
        filmSavedReqVO.setPreSaleNum("50000");
        filmSavedReqVO.setBoxOffice("90000");
        filmSavedReqVO.setFilmTypeId("1");
        filmSavedReqVO.setFilmSourceId("1");
        filmSavedReqVO.setFilmCatIds("1");
        filmSavedReqVO.setAreaId("1");
        filmSavedReqVO.setDateId("1");
        filmSavedReqVO.setFilmTime("2025-12-11");
        filmSavedReqVO.setDirectorId("1");
        filmSavedReqVO.setActIds("1,2");
        filmSavedReqVO.setRoleNames("家里人,管理员");
        filmSavedReqVO.setFilmLength("20");
        filmSavedReqVO.setBiography("SpringCloud 课程");
        filmSavedReqVO.setFilmImgs("/img/1.jpg,/img/2.jpg,/img/3.jpg,/img/4.jpg,/img/5.jpg");

        RestTemplate restTemplate = RestUtils.getRestTemplate();
        ResponseEntity<BaseResponseVO> response = restTemplate.postForEntity(url, filmSavedReqVO, BaseResponseVO.class);

        log.info("Addfilm response: {}",response);
        // 验证返回值的code
        Integer code = response.getBody().getCode();

        // 第一道拦截
        Assert.assertEquals(new Integer(200),code);
    }

    @Data
    public static class FilmSavedReqVO{

        private String filmStatus;
        private String filmName;
        private String filmEnName;
        private String mainImgAddress;
        private String filmScore;
        private String filmScorers;
        private String preSaleNum;
        private String boxOffice;
        private String filmTypeId;
        private String filmSourceId;
        private String filmCatIds;
        private String areaId;
        private String dateId;
        private String filmTime;
        private String directorId;
        private String actIds;       // actIds与roleNames需要一一对应
        private String roleNames;
        private String filmLength;
        private String biography;
        private String filmImgs;

    }
}
