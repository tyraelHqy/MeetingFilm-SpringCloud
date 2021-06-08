package com.mooc.meetingfilm.backend.common.backend.user;

import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import org.junit.Test;

public class UtilsTest {
    @Test
    public void test(){

        BaseResponseVO responseVO = new BaseResponseVO();
        System.out.println(responseVO.run("Hello World"));
    }
}
