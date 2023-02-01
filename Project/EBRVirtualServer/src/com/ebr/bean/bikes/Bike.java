package com.ebr.bean.bikes;

import java.math.BigDecimal;
import java.util.Date;

// enums
import com.ebr.enums.BikeStatus;

// interfaces
import com.ebr.interfaces.IBike;

// utils
import com.ebr.utils.Utils;

// consts
import com.ebr.constants.Constants;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("bike")
@JsonSubTypes({ @Type(value = RegularBike.class, name = "regularbike"), @Type(value = EBike.class, name = "ebike"),
		@Type(value = TwinBike.class, name = "twinbike") })
public abstract class Bike implements IBike {
	private String id;
	private String name;
	private float weight;
	private String licensePlate;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date manufacturingDate;
	private String producer;
	private BigDecimal cost;
	private String description;
	private BikeStatus status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Date getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BikeStatus getStatus() {
		return status;
	}

	public void setStatus(BikeStatus status) {
		this.status = status;
	}

	public abstract BigDecimal getRentalDepositPrice();

	public Bike() {
		super();
	}

	public Bike(String name, String licensePlate, String producer, String description) {
		super();
		this.name = name;
		this.licensePlate = licensePlate;
		this.producer = producer;
		this.description = description;
	}

	public Bike(String id, String name, String licensePlate, String producer, String description) {
		super();
		this.id = id;
		this.name = name;
		this.licensePlate = licensePlate;
		this.producer = producer;
		this.description = description;
	}

	public Bike(String name, float weight, String licensePlate, Date manufacturingDate, String producer,
			BigDecimal cost, String description, BikeStatus status) {
		super();
		this.name = name;
		this.weight = weight;
		this.licensePlate = licensePlate;
		this.manufacturingDate = manufacturingDate;
		this.producer = producer;
		this.cost = cost;
		this.description = description;
		this.status = status;
	}

	public Bike(String id, String name, float weight, String licensePlate, Date manufacturingDate, String producer,
			BigDecimal cost, String description, BikeStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.licensePlate = licensePlate;
		this.manufacturingDate = manufacturingDate;
		this.producer = producer;
		this.cost = cost;
		this.description = description;
		this.status = status;
	}

	@Override
	public String toString() {
		return "id: " + this.id + ", name: " + this.name + ", licensePlate: " + this.licensePlate + ", cost: "
				+ Constants.SystemSettings.CURRENCY_PREFIX + this.cost + Constants.SystemSettings.CURRENCY_SUFFIX
				+ ", status: " + this.status.getStatusStr();
	}

	public boolean match(Bike bike) {
		if (bike == null)
			return true;

		if (bike.id != null && !this.id.equals(bike.id))
			return false;

		if (bike.name != null && !bike.name.equals("") && !this.name.contains(bike.name))
			return false;

		if (bike.weight != 0 && this.weight != bike.weight)
			return false;

		if (bike.licensePlate != null && !bike.licensePlate.equals("")
				&& !this.licensePlate.contains(bike.licensePlate))
			return false;

		if (bike.manufacturingDate != null && !this.manufacturingDate.equals(bike.manufacturingDate))
			return false;

		if (bike.producer != null && !bike.producer.equals("") && !this.producer.contains(bike.producer))
			return false;

		if (bike.producer != null && !bike.producer.equals("") && !this.producer.contains(bike.producer))
			return false;

		if (bike.cost != null && this.cost != bike.cost)
			return false;

		if (bike.description != null && !bike.description.equals("") && !this.description.contains(bike.description))
			return false;

		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Bike)
			return this.id.equals(((Bike) obj).id);

		return false;
	}

	public abstract BigDecimal calculateRentPrice(long totalCalculationRentValue);

	@Override
	public BigDecimal calculateRentPrice(BigDecimal basePrice, BigDecimal additionalPricePerIncreaseValue,
			long totalCalculationRentValue) {
		BigDecimal totalRentPrice = Utils.convertDoubleToBigDecimal(0);

		if (totalCalculationRentValue <= 10)
			return totalRentPrice;

		// base price
		totalRentPrice = totalRentPrice.add(basePrice);

		if (totalCalculationRentValue > Constants.PriceCalculation.BASE_CALCULATION_RENT_VALUE) {

			long remainingRentValue = totalCalculationRentValue
					- Constants.PriceCalculation.BASE_CALCULATION_RENT_VALUE;

			while (remainingRentValue > 0) {
				totalRentPrice = totalRentPrice.add(additionalPricePerIncreaseValue);

				// substract base calculation rent value to get remaining rent value
				remainingRentValue -= Constants.PriceCalculation.PRICE_INCREASE_VALUE;
			}
		}

		return totalRentPrice;
	}
}