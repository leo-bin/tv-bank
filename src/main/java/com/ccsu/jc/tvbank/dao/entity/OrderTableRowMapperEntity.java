package com.ccsu.jc.tvbank.dao.entity;


import com.ccsu.jc.tvbank.domain.OrderTableEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


//用户表
public class OrderTableRowMapperEntity implements RowMapper<OrderTableEntity> {

	public OrderTableEntity mapRow(ResultSet arg0, int arg1) throws SQLException {
		OrderTableEntity ordertable = new OrderTableEntity();
		//user.setUseryinghangka(arg0.getString("useryinghangka"));
			  //grids.setGridsID(arg0.getString("gridsID"));
		ordertable.setOrderID(arg0.getString("orderID"));
		ordertable.setOrdergridsImg(arg0.getString("ordergridsImg"));
		ordertable.setOrderIgridsName(arg0.getString("orderIgridsName"));
		ordertable.setOrderStat(arg0.getString("orderStat"));
		ordertable.setOrderTime(arg0.getString("orderTime"));
		ordertable.setOrderuserName(arg0.getString("orderuserName"));
		ordertable.setOrderzongRMB(arg0.getString("orderzongRMB"));
		ordertable.setOrderAddr(arg0.getString("orderAddr"));
		return ordertable;
	}

	
	
}
