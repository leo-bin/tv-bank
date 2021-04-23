package com.ccsu.jc.tvbank.service.impl;

import com.ccsu.jc.tvbank.dao.impl.LoginDaoimpl;
import com.ccsu.jc.tvbank.domain.OrderTableEntity;
import com.ccsu.jc.tvbank.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDaoimpl loginService;

    public boolean loginuser(String userName, String passWord) {
        int num = loginService.login(userName, passWord);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public boolean paypassword(String userName, String pass) {
        int num = loginService.paypassword(userName, pass);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public boolean updateRMB(String userRMB, String userName) {
        int num = loginService.updateRMB(userRMB, userName);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public boolean ordertable(OrderTableEntity ordertable) {
        int num = loginService.ordertable(ordertable);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public boolean Confirmorder(String orderID) {
        int num = loginService.Confirmorder(orderID);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public boolean Cancellationoforder(String orderID) {
        int num = loginService.Cancellationoforder(orderID);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public boolean delordertable(String orderID) {
        int num = loginService.delordertable(orderID);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public boolean Delivergoods(String OrderStat, String orderID) {
        int num = loginService.Delivergoods(OrderStat, orderID);
        if (num > 0) {
            return true;
        }
        return false;
    }

}
