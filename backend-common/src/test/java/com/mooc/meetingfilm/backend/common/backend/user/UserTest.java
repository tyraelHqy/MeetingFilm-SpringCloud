package com.mooc.meetingfilm.backend.common.backend.user;

import com.mooc.meetingfilm.backend.common.BackendCommonApplication;
import com.mooc.meetingfilm.backend.common.BackendCommonApplicationTests;
import com.mooc.meetingfilm.backend.common.dao.entity.MoocBackendUserT;
import com.mooc.meetingfilm.backend.common.dao.mapper.MoocBackendUserTMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserTest extends BackendCommonApplicationTests {

    @Resource
    private MoocBackendUserTMapper backendUser;

    @Test
    public void add(){
        MoocBackendUserT user = new MoocBackendUserT();
        user.setUserName("admin");
        user.setUserPwd("admin");
        user.setUserPhone("13100000000");

        backendUser.insert(user);
    }

    @Test
    public void select(){
        // 根据主键查询
        MoocBackendUserT moocBackendUserT = backendUser.selectById(2);
        System.out.println("moocBackendUserT: "+moocBackendUserT);

        // 查询列表
        List<MoocBackendUserT> users = backendUser.selectList(null);
        users.stream().forEach(
                System.out::println
        );
    }

    @Test
    public void update(){
        MoocBackendUserT user = new MoocBackendUserT();
        user.setUuid(2);
        user.setUserName("admin");
        user.setUserPwd("Tyrael");
        user.setUserPhone("18511110000");

        backendUser.updateById(user);
    }

    @Test
    public void delete(){
        backendUser.deleteById(2);
    }
}
