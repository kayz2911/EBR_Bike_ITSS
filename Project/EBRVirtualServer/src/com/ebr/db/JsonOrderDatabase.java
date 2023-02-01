package com.ebr.db;

import java.util.ArrayList;
import java.util.Date;

// beans
import com.ebr.bean.order.Order;

// db interfaces
import com.ebr.db.interfaces.IOrderDatabase;

// db seed
import com.ebr.db.seed.Seed;

// utils
import com.ebr.utils.Utils;

public class JsonOrderDatabase implements IOrderDatabase {
	private static IOrderDatabase singleton = new JsonOrderDatabase();

	private ArrayList<Order> orders = Seed.singleton().getOrders();

	private JsonOrderDatabase() {
	}

	public static IOrderDatabase singleton() {
		return singleton;
	}

	@Override
	public ArrayList<Order> search(Order order) {
		ArrayList<Order> res = new ArrayList<Order>();

		for (Order o : orders) {
			if (o.match(order)) {
				res.add(o);
			}
		}

		return res;
	}

	@Override
	public Order getById(String orderId) {
		if (orderId != null) {
			for (Order o : orders) {
				if (o.getId().equals(orderId)) {
					return o;
				}
			}
		}

		return null;
	}

	@Override
	public Order getOrderByBikeId(String bikeId) {
		if (bikeId != null) {
			for (Order o : orders) {
				if (o.getBikeId().equals(bikeId)) {
					return o;
				}
			}
		}

		return null;
	}

	@Override
	public ArrayList<Order> getOrdersByCustomerId(String customerId) {
		ArrayList<Order> res = new ArrayList<Order>();

		if (customerId != null) {
			for (Order o : orders) {
				if (o.getCustomerId().equals(customerId)) {
					res.add(o);
				}
			}
		}

		return res;
	}

	@Override
	public Order add(Order order) {
		for (Order o : orders) {
			if (o.equals(order)) {
				return null;
			}
		}

		if (order.getId() == null || order.getId().equals(""))
			order.setId(Utils.generateUUID());

		// set time
		if (order.getCreatedTime() == null)
			order.setCreatedTime(new Date());
		if (order.getUpdatedTime() == null)
			order.setUpdatedTime(new Date());

		orders.add(order);

		return order;
	}

	@Override
	public Order update(Order order) {
		for (Order o : orders) {
			if (o.equals(order)) {
				// set time
				order.setCreatedTime(o.getCreatedTime());
				order.setUpdatedTime(new Date());

				orders.remove(o);
				orders.add(order);
				return order;
			}
		}

		return null;
	}

	@Override
	public boolean checkExistById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean removeById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
