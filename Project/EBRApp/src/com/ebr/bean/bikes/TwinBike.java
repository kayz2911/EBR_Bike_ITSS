package com.ebr.bean.bikes;

import java.math.BigDecimal;
import java.util.Date;

// enums
import com.ebr.enums.BikeStatus;
import com.ebr.utils.Utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwinBike extends Bike {
	private final BigDecimal RENTAL_DEPOSIT_PRICE = Utils.convertDoubleToBigDecimal(550000);
	private final BigDecimal RENTAL_BASE_PRICE = Utils.convertDoubleToBigDecimal(15000);
	private final BigDecimal ADDTIONAL_PRICE_PER_INCREASE_VALUE = Utils.convertDoubleToBigDecimal(4500);

	@Override
	public BigDecimal getRentalDepositPrice() {
		return this.RENTAL_DEPOSIT_PRICE;
	}

	public TwinBike() {
		super();
	}

	public TwinBike(String id, String name, String licensePlate, String producer, String description) {
		super(id, name, licensePlate, producer, description);
	}

	public TwinBike(String name, String licensePlate, String producer, String description) {
		super(name, licensePlate, producer, description);
	}

	public TwinBike(String name, float weight, String licensePlate, Date manufacturingDate, String producer,
			BigDecimal cost, String description, BikeStatus status) {
		super(name, weight, licensePlate, manufacturingDate, producer, cost, description, status);
	}

	public TwinBike(String id, String name, float weight, String licensePlate, Date manufacturingDate, String producer,
			BigDecimal cost, String description, BikeStatus status) {
		super(id, name, weight, licensePlate, manufacturingDate, producer, cost, description, status);
	}

	@Override
	public boolean match(Bike bike) {
		if (bike == null)
			return true;

		if (!(bike instanceof TwinBike))
			return false;

		boolean res = super.match(bike);
		if (!res) {
			return false;
		}

		return true;
	}

	@Override
	public BigDecimal calculateRentPrice(long totalCalculationRentValue) {
		return super.calculateRentPrice(this.RENTAL_BASE_PRICE, this.ADDTIONAL_PRICE_PER_INCREASE_VALUE,
				totalCalculationRentValue);
	}
}