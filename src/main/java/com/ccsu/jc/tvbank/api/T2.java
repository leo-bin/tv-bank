package com.ccsu.jc.tvbank.api;

import com.ccsu.jc.tvbank.domain.UserEntity;
import com.ccsu.jc.tvbank.service.impl.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class T2 {
	
	@Autowired
	RegisterServiceImpl registerService;
	
	@RequestMapping("registertest.sf")
	public String web3(){
		System.out.println("测试是否进入此方法");
		UserEntity user = new UserEntity();
		user.setUserID("1238");
		user.setUserName("123123");
		user.setUserMingzi("123123");
		user.setUserState("1");
		user.setUserPhone("123123");
		user.setPassWord("123");
		
		boolean bl=registerService.RegisterService(user);
		if(bl){
			System.out.println("注册成功");
		}else{
			System.out.println("注册失败");
		}
		return "login";
	}

}
