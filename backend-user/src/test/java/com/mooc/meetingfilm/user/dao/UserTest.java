package com.mooc.meetingfilm.user.dao;

import com.mooc.meetingfilm.user.BackendUserApplicationTests;
import com.mooc.meetingfilm.user.dao.entity.MoocBackendUserT;
import com.mooc.meetingfilm.user.dao.mapper.MoocBackendUserTMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;


public class UserTest extends BackendUserApplicationTests {

    @Resource
    public MoocBackendUserTMapper backendUser;

    @Test
    public void add(){

    }

    @Test
    public void select(){
        // 查询列表带条件
        List<MoocBackendUserT> users = backendUser.selectList(null);
        users.stream().forEach(
                System.out::println
        );
    }

}
