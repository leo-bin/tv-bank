package com.ccsu.jc.tvbank.service.impl;

import com.ccsu.jc.tvbank.dao.impl.LoginDaoimpl;
import com.ccsu.jc.tvbank.service.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddServiceImpl implements AddService {

    @Autowired
    LoginDaoimpl login;

    public boolean login() {
        int num = login.login("1", "1");
        if (num > 0) {
            return true;
        }
        return false;
    }

}
