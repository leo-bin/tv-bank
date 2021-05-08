package com.ccsu.jc.tvbank.service.impl;

import com.ccsu.jc.tvbank.dao.UserDAO;
import com.ccsu.jc.tvbank.domain.*;
import com.ccsu.jc.tvbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean login(String userName, String passWord) {
        UserEntity user = userDAO.select(userName);
        if (user == null) {
            return false;
        }
        return user.getPassWord().equals(passWord);
    }

    @Override
    public boolean register(UserEntity userEntity) {
        UserEntity user = userDAO.select(userEntity.getUserName());
        if (user != null) {
            return false;
        }
        int i = userDAO.insert(userEntity);
        return i >= 1;
    }

    @Override
    public UserEntity userInfo(String userName) {
        return userDAO.select(userName);
    }

    @Override
    public List<UserEntity> userList(int pageInt) {
        List<UserEntity> list;
        list = userDAO.userlistpage(pageInt);
        return list;
    }

    @Override
    public List<UserEntity> listmohu(String usermohu) {
        List<UserEntity> list = userDAO.listmohu(usermohu);
        return list;
    }

    @Override
    public List<UserEntity> userPhone(String userPhone) {
        List<UserEntity> list = userDAO.userPhone(userPhone);
        return list;
    }

    @Override
    public List<UserEntity> userID(String userID) {
        List<UserEntity> list = userDAO.userID(userID);
        return list;
    }


    @Override
    public boolean updateLoginPassword(String userName, String newpassWord) {
        UserEntity userEntity = userDAO.select(userName);
        if (userEntity == null) {
            return false;
        }
        userEntity.setPassWord(newpassWord);
        int num = userDAO.updateUser(userEntity);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateLoginEmail(String userName, String newEmail) {
        UserEntity userEntity = userDAO.select(userName);
        if (userEntity == null) {
            return false;
        }
        userEntity.setUserEmail(newEmail);
        int num = userDAO.updateUser(userEntity);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateLoginPhone(String userName, String newuserPhone) {
        UserEntity userEntity = userDAO.select(userName);
        if (userEntity == null) {
            return false;
        }
        userEntity.setUserPhone(newuserPhone);
        int num = userDAO.updateUser(userEntity);
        if (num > 0) {
            return true;
        }
        return false;
    }


    @Override
    public boolean updateLoginHand(String userName, String newuserHand) {
        UserEntity userEntity = userDAO.select(userName);
        if (userEntity == null) {
            return false;
        }
        userEntity.setUserHand(newuserHand);
        int num = userDAO.updateUser(userEntity);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(UserEntity user) {
        int num = userDAO.updateUser(user);
        if (num > 0) {
            return true;
        }
        return false;
    }


}
