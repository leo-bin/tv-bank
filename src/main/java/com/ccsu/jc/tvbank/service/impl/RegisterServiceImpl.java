package com.ccsu.jc.tvbank.service.impl;

import com.ccsu.jc.tvbank.dao.impl.RegisterImpl;
import com.ccsu.jc.tvbank.domain.UserEntity;
import com.ccsu.jc.tvbank.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    RegisterImpl registerImpl;

    public boolean RegisterService(UserEntity user) {
        int num = registerImpl.Register(user);
        if (num > 0) {
            //注册成功
            return true;
        }
        return false;
    }

}
