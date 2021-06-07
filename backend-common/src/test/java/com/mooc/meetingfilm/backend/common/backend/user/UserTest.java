package com.mooc.meetingfilm.backend.common.backend.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
        for (int i = 0; i < 5; i++) {
            MoocBackendUserT user = new MoocBackendUserT();
            user.setUserName("admin"+i);
            user.setUserPwd("admin"+i);
            user.setUserPhone("1310000000"+i);
            backendUser.insert(user);
        }
//        MoocBackendUserT user = new MoocBackendUserT();
//        user.setUserName("admin");
//        user.setUserPwd("admin");
//        user.setUserPhone("13100000000");
//
//        backendUser.insert(user);
    }

    @Test
    public void select(){
        // 根据主键查询
//        MoocBackendUserT moocBackendUserT = backendUser.selectById(2);
//        System.out.println("moocBackendUserT: "+moocBackendUserT);
//
//        // 查询列表
//        List<MoocBackendUserT> users = backendUser.selectList(null);
//        users.stream().forEach(
//                System.out::println
//        );

        // 查询列表带条件
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("user_name","admin");
        List<MoocBackendUserT> user = backendUser.selectList(queryWrapper);
        user.stream().forEach(
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

        // 根据主键修改
        backendUser.updateById(user);

        // 根据条件修改
        QueryWrapper<MoocBackendUserT> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","admin4");
        MoocBackendUserT user2 = new MoocBackendUserT();
        user2.setUserName("admin");
        user2.setUserPwd("Tyrael");
        user2.setUserPhone("18511110000");
        int update = backendUser.update(user2, wrapper);
    }

    @Test
    public void delete(){
        backendUser.deleteById(2);
    }
}
