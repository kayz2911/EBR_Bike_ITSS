package com.ebr.bean.order;

import java.math.BigDecimal;
import java.util.*;

// enums
import com.ebr.enums.OrderStatus;

// beans
import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikestation.BikeStation;
import com.ebr.bean.customer.Customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
	private String id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date createdTime;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date updatedTime;
	private String customerId;
	private String bikeId;
	private OrderStatus status;
	private long totalRentTime;
	private BigDecimal price;

	// display data
	private Customer customerInfo;
	private Bike bikeInfo;
	private BikeStation bikeStationInfo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBikeId() {
		return bikeId;
	}

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}

	public Customer getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(Customer customerInfo) {
		this.customerInfo = customerInfo;
	}

	public Bike getBikeInfo() {
		return bikeInfo;
	}

	public void setBikeInfo(Bike bikeInfo) {
		this.bikeInfo = bikeInfo;
	}

	public BikeStation getBikeStationInfo() {
		return bikeStationInfo;
	}

	public void setBikeStationInfo(BikeStation bikeStationInfo) {
		this.bikeStationInfo = bikeStationInfo;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public long getTotalRentTime() {
		return totalRentTime;
	}

	public void setTotalRentTime(long totalRentTime) {
		this.totalRentTime = totalRentTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public long calculateTotalRentTime() {
		return Math.abs(this.updatedTime.getTime() - this.createdTime.getTime()) / 1000;
	}

	public BigDecimal calculateTotalRentPrice() {
		if (bikeInfo == null)
			return null;

		long totalRentTime = this.calculateTotalRentTime();
		return bikeInfo.calculateRentPrice(totalRentTime);
	}

	public Order() {
		super();
	}

	public Order(String id, String customerId, String bikeId, OrderStatus status) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.bikeId = bikeId;
		this.status = status;
	}

	public Order(String customerId, String bikeId, OrderStatus status) {
		super();
		this.customerId = customerId;
		this.bikeId = bikeId;
		this.status = status;
	}

	@Override
	public String toString() {
		return "id: " + this.id + ", customerId: " + this.customerId + ", bikeId: " + this.bikeId + ", status: "
				+ this.status.getStatusStr() + ", price: " + this.price + ", createdTime: "
				+ this.createdTime.toString() + ", updatedTime: " + this.updatedTime.toString();
	}

	public boolean match(Order order) {
		if (order == null)
			return true;

		if (order.id != null && !this.id.equals(order.id))
			return false;

		if (order.customerId != null && !this.customerId.equals(order.customerId))
			return false;

		if (order.bikeId != null && !this.bikeId.equals(order.bikeId))
			return false;

		if (order.status != null && !this.status.equals(order.status))
			return false;

		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Order)
			return this.id.equals(((Order) obj).id);

		return false;
	}
}