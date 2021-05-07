package com.ccsu.jc.tvbank.dao.entity;


import com.ccsu.jc.tvbank.domain.ShoppingCart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


//用户表
public class ShoppingCartRowMapperEntity implements RowMapper<ShoppingCart> {

	public ShoppingCart mapRow(ResultSet arg0, int arg1) throws SQLException {
		//gridsEntity grids = new gridsEntity();
		//user.setUseryinghangka(arg0.getString("useryinghangka"));
		ShoppingCart shoopingCart = new ShoppingCart();
		shoopingCart.setCartID(arg0.getString("cartID"));
		shoopingCart.setShoopingID(arg0.getString("shoopingID"));
		shoopingCart.setShoopingImg(arg0.getString("shoopingImg"));
		shoopingCart.setShoopingjiage(arg0.getString("shoopingjiage"));
		shoopingCart.setShoopingName(arg0.getString("shoopingName"));
		shoopingCart.setUserName(arg0.getString("userName"));
		return shoopingCart;
	}

	
	
}