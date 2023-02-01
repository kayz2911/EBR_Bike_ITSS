package com.ebr.db.interfaces;

import java.util.ArrayList;

// beans
import com.ebr.bean.order.Order;

public interface IOrderDatabase extends IDataManageDatabase<Order>, IDataSearchDatabase<Order> {
	public Order getOrderByBikeId(String orderId);

	public ArrayList<Order> getOrdersByCustomerId(String orderId);
}
