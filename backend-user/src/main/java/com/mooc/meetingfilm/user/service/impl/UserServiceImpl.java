package com.mooc.meetingfilm.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mooc.meetingfilm.user.dao.entity.MoocBackendUserT;
import com.mooc.meetingfilm.user.dao.mapper.MoocBackendUserTMapper;
import com.mooc.meetingfilm.user.service.UserServiceAPI;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserServiceAPI {

    @Resource
    private MoocBackendUserTMapper userMapper;

    @Override
    public String checkUserLogin(String username, String password) throws CommonServiceException {
        // 根据用户名获取用户信息
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);

        // 避免数据出现问题
        List<MoocBackendUserT> list = userMapper.selectList(wrapper);
        MoocBackendUserT user = null;

        if (list != null && list.size() > 0) {
            user = list.stream().findFirst().get();
        }else {
            throw new CommonServiceException(404,"用户名输入有误");
        }

        // 验证密码是否正确【密码要做MD5加密，才能验证是否匹配】
        String encrypt = MD5Util.encrypt(password);
        if(!encrypt.equals(user.getUserPwd())){
            throw new CommonServiceException(500,"用户密码输入有误");
        }else {
            return user.getUuid()+"";
        }
    }
}
