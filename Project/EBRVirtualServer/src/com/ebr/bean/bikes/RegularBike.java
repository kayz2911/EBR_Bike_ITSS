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
public class RegularBike extends Bike {
	private final BigDecimal RENTAL_DEPOSIT_PRICE = Utils.convertDoubleToBigDecimal(400000);
	private final BigDecimal RENTAL_BASE_PRICE = Utils.convertDoubleToBigDecimal(10000);
	private final BigDecimal ADDTIONAL_PRICE_PER_INCREASE_VALUE = Utils.convertDoubleToBigDecimal(3000);

	@Override
	public BigDecimal getRentalDepositPrice() {
		return this.RENTAL_DEPOSIT_PRICE;
	}

	public RegularBike() {
		super();
	}

	public RegularBike(String name, String licensePlate, String producer, String description) {
		super(name, licensePlate, producer, description);
	}

	public RegularBike(String id, String name, String licensePlate, String producer, String description) {
		super(id, name, licensePlate, producer, description);
	}

	public RegularBike(String name, float weight, String licensePlate, Date manufacturingDate, BigDecimal cost,
			String producer, String description, BikeStatus status) {
		super(name, weight, licensePlate, manufacturingDate, producer, cost, description, status);
	}

	public RegularBike(String id, String name, float weight, String licensePlate, Date manufacturingDate,
			BigDecimal cost, String producer, String description, BikeStatus status) {
		super(id, name, weight, licensePlate, manufacturingDate, producer, cost, description, status);
	}

	@Override
	public boolean match(Bike bike) {
		if (bike == null)
			return true;

		boolean res = super.match(bike);

		if (!res)
			return false;

		if (!(bike instanceof RegularBike))
			return false;

		return true;
	}

	@Override
	public BigDecimal calculateRentPrice(long totalCalculationRentValue) {
		return super.calculateRentPrice(this.RENTAL_BASE_PRICE, this.ADDTIONAL_PRICE_PER_INCREASE_VALUE,
				totalCalculationRentValue);
	}
}