package com.ebr.service.order;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// enums
import com.ebr.enums.BikeStatus;
import com.ebr.enums.OrderStatus;
import com.ebr.utils.Utils;
// beans
import com.ebr.bean.order.Order;
import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.EBike;
import com.ebr.bean.bikes.RegularBike;
import com.ebr.bean.bikes.TwinBike;
import com.ebr.bean.bikestation.BikeDock;
import com.ebr.bean.customer.Customer;
import com.ebr.bean.customer.Card;

// db interfaces
import com.ebr.db.interfaces.IOrderDatabase;
import com.ebr.db.interfaces.ICustomerDatabase;
import com.ebr.db.interfaces.ICardDatabase;
import com.ebr.db.interfaces.IBankAccountDatabase;
import com.ebr.db.interfaces.IBikeDatabase;
import com.ebr.db.interfaces.IBikeStationDatabase;
import com.ebr.db.interfaces.IBikeDockDatabase;

// dbs
import com.ebr.db.JsonOrderDatabase;
import com.ebr.db.JsonCustomerDatabase;
import com.ebr.db.JsonCardDatabase;
import com.ebr.db.JsonBankAccountDatabase;
import com.ebr.db.JsonBikeDatabase;
import com.ebr.db.JsonBikeStationDatabase;
import com.ebr.db.JsonBikeDockDatabase;

@Path("/orders")
public class OrderService {
	private IOrderDatabase orderDatabase;
	private ICustomerDatabase customerDatabase;
	private ICardDatabase cardDatabase;
	private IBikeDatabase bikeDatabase;
	private IBikeStationDatabase bikeStationDatabase;
	private IBikeDockDatabase bikeDockDatabase;
	private IBankAccountDatabase bankDatabase;

	public OrderService() {
		orderDatabase = JsonOrderDatabase.singleton();
		customerDatabase = JsonCustomerDatabase.singleton();
		cardDatabase = JsonCardDatabase.singleton();
		bikeDatabase = JsonBikeDatabase.singleton();
		bikeStationDatabase = JsonBikeStationDatabase.singleton();
		bikeDockDatabase = JsonBikeDockDatabase.singleton();
		bankDatabase = JsonBankAccountDatabase.singleton();
	}

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Order> getOrders(@QueryParam("id") String id, @QueryParam("customerId") String customerId,
			@QueryParam("bikeId") String bikeId, @QueryParam("status") String status) {
		Order order = new Order(id, customerId, bikeId,
				status != null && !status.trim().equals("") ? OrderStatus.valueOf(status) : null);
		ArrayList<Order> res = orderDatabase.search(order);

		for (Order o : res) {
			// get more info: customer, bike
			o = setOrderAdditionalInfo(o);
		}

		return res;
	}

	@GET
	@Path("/get-order-by-id")
	@Produces(MediaType.APPLICATION_JSON)
	public Order getById(@QueryParam("id") String id) {
		Order res = orderDatabase.getById(id);

		// get more info: customer, bike
		res = setOrderAdditionalInfo(res);

		return res;
	}

	@GET
	@Path("/get-order-by-bike-id")
	@Produces(MediaType.APPLICATION_JSON)
	public Order getOrderByBikeId(@QueryParam("bikeId") String bikeId) {
		Order res = orderDatabase.getOrderByBikeId(bikeId);

		// get more info: customer, bike
		res = setOrderAdditionalInfo(res);

		return res;
	}

	@GET
	@Path("/get-orders-by-customer-id")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Order> getOrdersByCustomerId(@QueryParam("customerId") String customerId) {
		ArrayList<Order> res = orderDatabase.getOrdersByCustomerId(customerId);

		for (Order o : res) {
			// get more info: customer, bike
			o = setOrderAdditionalInfo(o);
		}

		return res;
	}

	@GET
	@Path("/get-renting-order-by-customer-id")
	@Produces(MediaType.APPLICATION_JSON)
	public Order getRentingOrderByCustomerId(@QueryParam("customerId") String customerId) {
		ArrayList<Order> res = orderDatabase.getOrdersByCustomerId(customerId);

		for (Order o : res) {
			if (o.getStatus() == OrderStatus.APPROVED) {
				// get more info: customer, bike
				o = setOrderAdditionalInfo(o);
				return o;
			}
		}

		return null;
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Order add(Order order) throws Exception {
		// check if bike is available
		if (!bikeDatabase.checkBikeAvailableByBikeId(order.getBikeId()))
			throw new Exception("Error: Bike is not available!");

		// auto approve order
		order.setStatus(OrderStatus.APPROVED);

		// if customer not exist then add customer
		if (order.getCustomerId() == null || order.getCustomerId().equals("")) {
			// check customer info is provided
			if (order.getCustomerInfo() == null)
				throw new Exception("Error: Customer must be provided!");

			// check customer card info is provided
			if (order.getCustomerInfo().getCard() == null)
				throw new Exception("Error: Customer's card must be provided!");

			// add card
			Card card = cardDatabase.add(order.getCustomerInfo().getCard());
			order.getCustomerInfo().setCardId(card.getId());

			// add customer
			Customer customer = customerDatabase.add(order.getCustomerInfo());
			order.setCustomerId(customer.getId());
		}

		Order newOrder = orderDatabase.add(order);

		if (newOrder.getStatus().equals(OrderStatus.APPROVED)) { // approved order
			// remove bike dock
			bikeDockDatabase.removeByBikeId(newOrder.getBikeId());

			// update bike status to rented
			bikeDatabase.updateBikeStatusByBikeId(newOrder.getBikeId(), BikeStatus.RENTED);
		} else if (newOrder.getStatus().equals(OrderStatus.REQUESTING)) { // requesting order
			// update bike status to holding
			bikeDatabase.updateBikeStatusByBikeId(newOrder.getBikeId(), BikeStatus.HOLDING);
		}

		return newOrder;
	}

	@POST
	@Path("/update/{bikeStationId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Order update(@PathParam("bikeStationId") String bikeStationId, Order order) throws Exception {
		if (order.getStatus().equals(OrderStatus.APPROVED)) { // approved order
			// remove bike dock
			bikeDockDatabase.removeByBikeId(order.getBikeId());

			// update bike status to rented
			bikeDatabase.updateBikeStatusByBikeId(order.getBikeId(), BikeStatus.RENTED);
		} else if (order.getStatus().equals(OrderStatus.REJECTED)) { // rejected order
			// update bike status to available
			bikeDatabase.updateBikeStatusByBikeId(order.getBikeId(), BikeStatus.AVAILABLE);
		} else if (order.getStatus().equals(OrderStatus.SUCCEED)) { // succeed order
			if (bikeStationId == null || bikeStationId.equals(""))
				throw new Exception("Error: Bike station id is not provided!");

			// check bike station exist by id
			if (!bikeStationDatabase.checkExistById(bikeStationId))
				throw new Exception("Error: Bike station is not existed!");

			Order updatedOrder = orderDatabase.update(order);

			// calculate money and refund if need
			long totalRentingTimeInMinutes = Math
					.abs(updatedOrder.getUpdatedTime().getTime() - updatedOrder.getCreatedTime().getTime()) / 1000;

			updatedOrder.setTotalRentTime(totalRentingTimeInMinutes);

			orderDatabase.update(updatedOrder);

			// set additional infor: customer, info
			updatedOrder = setOrderAdditionalInfo(updatedOrder);

			// get card info
			Card cardInfo = cardDatabase.getById(updatedOrder.getCustomerInfo().getCardId());

			BigDecimal totalRentPrice = updatedOrder.calculateTotalRentPrice();

			switch (totalRentPrice.compareTo(updatedOrder.getBikeInfo().getRentalDepositPrice())) {
				// totalRentPrice = rental deposit price
				// no changes
				case 0:
					break;
				// totalRentPrice > rental deposit price
				// then withdraw the amount of totalRentPrice - rental deposit price
				case 1:
					boolean resWithDraw = bankDatabase.withdraw(cardInfo.getCardNumber(),
							totalRentPrice.subtract(updatedOrder.getBikeInfo().getRentalDepositPrice()));
					if (!resWithDraw)
						return null;
					break;
				// totalRentPrice < rental deposit price
				// then topup the amount of rental deposit price - totalRentPrice
				case -1:
					boolean resTopup = bankDatabase.topup(cardInfo.getCardNumber(),
							updatedOrder.getBikeInfo().getRentalDepositPrice().subtract(totalRentPrice));
					if (!resTopup)
						return null;
					break;
				default:
					break;
			}

			// update bike status to available
			bikeDatabase.updateBikeStatusByBikeId(updatedOrder.getBikeId(), BikeStatus.AVAILABLE);

			// add bike dock
			bikeDockDatabase.add(new BikeDock(null, bikeStationId, updatedOrder.getBikeId()));

			return updatedOrder;
		}

		return null;

	}

	// pragma region PRIVATE METHODS
	/**
	 * get more order info: customer, bike
	 * 
	 * @param {Order} order
	 * 
	 */
	private Order setOrderAdditionalInfo(Order order) {
		if (order == null)
			return null;

		// get customer info
		order.setCustomerInfo(customerDatabase.getById(order.getCustomerId()));

		// get bike info
		order.setBikeInfo(bikeDatabase.getById(order.getBikeId()));

		return order;
	}
	// pragma endregion
}