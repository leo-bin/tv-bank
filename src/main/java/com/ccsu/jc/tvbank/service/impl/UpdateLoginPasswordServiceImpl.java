package com.ccsu.jc.tvbank.service.impl;

import com.ccsu.jc.tvbank.dao.impl.UpdateLoginPasswordImpl;
import com.ccsu.jc.tvbank.domain.UserEntity;
import com.ccsu.jc.tvbank.service.UpdateLoginPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateLoginPasswordServiceImpl implements UpdateLoginPasswordService {

    @Autowired
    UpdateLoginPasswordImpl update_login_password_Impl;

    public boolean Update_login_password(String userName, String passWord, String newpassWord) {
        int num = update_login_password_Impl.Update_login_password(userName, passWord, newpassWord);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public boolean Update_login_Emial(String userName, String emial, String newemial) {

        int num = update_login_password_Impl.Update_login_Emial(userName, emial, newemial);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public boolean Update_login_Phone(String userName, String userPhone, String newuserPhone) {
        int num = update_login_password_Impl.Update_login_Phone(userName, userPhone, newuserPhone);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public boolean Update_login_payPassword(String userName, String paypassword, String newpaypassword) {
        int num = update_login_password_Impl.Update_login_payPassword(userName, paypassword, newpaypassword);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public boolean Update_login_hand(String userName, String userHand, String newuserHand) {
        int num = update_login_password_Impl.Update_login_hand(userName, userHand, newuserHand);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public boolean Update_user(UserEntity user) {
        int num = update_login_password_Impl.Update_user(user);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public boolean Update_Addred(String sessionName, String dizhi, String xingming, String phone) {
        int i = update_login_password_Impl.Update_Addred(sessionName, dizhi, xingming, phone);
        if (i > 0) {
            return true;
        }
        return false;
    }

    public boolean delectcartID(String cartID) {
        int num = update_login_password_Impl.delectcartID(cartID);
        if (num > 0) {
            return true;
        }
        return false;
    }

}
