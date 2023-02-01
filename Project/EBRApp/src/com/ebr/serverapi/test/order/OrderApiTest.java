package com.ebr.serverapi.test.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ebr.bean.Address;
import com.ebr.bean.customer.Card;
import com.ebr.bean.customer.Customer;
import com.ebr.bean.order.Order;
import com.ebr.enums.BikeStatus;
import com.ebr.enums.OrderStatus;
import com.ebr.serverapi.orders.OrderApi;
import com.ebr.utils.Utils;

public class OrderApiTest {
	private OrderApi api = new OrderApi();

	@Test
	public void testGetAllOrders() {
		ArrayList<Order> list = api.getOrders(null);
		assertEquals("Error in getOrders API!", list.size(), 5);
	}

	@Test
	public void testGetAllOrdersWithParamId() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "69122546-97e4-487a-9b12-eccaff4f43b5");
		ArrayList<Order> list = api.getOrders(params);
		assertTrue("Error in testGetAllOrdersWithParamId API!", list.size() == 1);
	}

	@Test
	public void testGetAllOrdersWithParamCustomerId() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("customerId", "06a9ea28-2997-401d-b7a5-491b3f14f768");
		ArrayList<Order> list = api.getOrders(params);
		assertTrue("Error in testGetAllOrdersWithParamCustomerId API!", list.size() == 1);
	}

	@Test
	public void testGetAllOrdersWithParamBikeId() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bikeId", "e5e99272-a066-40cc-88a6-1b49db43f8ca");
		ArrayList<Order> list = api.getOrders(params);
		assertTrue("Error in testGetAllOrdersWithParamBikeId API!", list.size() == 1);
	}

	@Test
	public void testGetAllOrdersWithParamStatus() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("status", "APPROVED");
		ArrayList<Order> list = api.getOrders(params);
		assertTrue("Error in testGetAllOrdersWithParamStatus API!", list.size() == 3);
	}

	@Test(timeout = 1000)
	public void testResponse() {
		api.getOrders(null);
	}

	@Test
	public void testAddOrderWithExistUser() {
		Order order = new Order("3245412b-6a33-4330-a644-b423c29e843r", "73477dcc-f130-4140-9939-1c3179f71f01",
				OrderStatus.APPROVED);

		api.addOrder(order);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bikeId", "73477dcc-f130-4140-9939-1c3179f71f01");
		ArrayList<Order> list = api.getOrders(params);
		assertTrue("Error in addOrder API!", list.size() > 0);

		Order newOrder = api.getOrders(params).get(0);
		assertEquals("Error in addOrder API!", newOrder.getCustomerId(), order.getCustomerId());
		assertEquals("Error in addOrder API!", newOrder.getBikeId(), order.getBikeId());
		assertEquals("Error in addOrder API!", newOrder.getStatus(), order.getStatus());
	}

	@Test
	public void testAddOrderWithNonExistUser() {
		Order order = new Order();
		order.setBikeId("c9c4ddef-3908-4c4a-83c9-6af27f86021d");
		Customer customer = new Customer();
		customer.setFirstName("Đức");
		customer.setLastName("Nguyễn Hữu");
		customer.setPhoneNumber("+1 354-542-7908");
		Card card = new Card();
		card.setCardholderName("NGUYEN HUU DUC");
		card.setCardNumber("5423099219111453");
		card.setIssuingBank("MasterCard");
		try {
			card.setExpirationDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/03/2025 03:44:11"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		card.setCVC(523);
		customer.setCard(card);
		order.setCustomerInfo(customer);
		order.setStatus(OrderStatus.APPROVED);

		api.addOrder(order);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bikeId", "c9c4ddef-3908-4c4a-83c9-6af27f86021d");
		ArrayList<Order> list = api.getOrders(params);
		assertTrue("Error in addOrder API!", list.size() > 0);

		Order newOrder = api.getOrders(params).get(0);
		assertEquals("Error in addOrder API!", newOrder.getBikeId(), order.getBikeId());
		assertEquals("Error in addOrder API!", newOrder.getStatus(), order.getStatus());
	}

	@Test
	public void testUpdateOrder() {
		ArrayList<Order> list = api.getOrders(null);
		assertTrue("No data", list.size() > 0);

		Order order = list.get(0);
		OrderStatus newStatus = OrderStatus.SUCCEED;
		order.setStatus(newStatus);
		api.updateOrder(order, "7f7388f9-2d63-4ab2-b270-daaa0699e80e");

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", order.getId());
		list = api.getOrders(params);
		assertTrue("Error in updateOrder API!", list.size() > 0);

		Order newOrder = api.getOrders(params).get(0);
		assertEquals("Error in updateOrder API!", newOrder.getStatus(), newStatus);
	}
}