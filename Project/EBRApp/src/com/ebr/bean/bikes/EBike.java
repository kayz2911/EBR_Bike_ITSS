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
public class EBike extends Bike {
	private final BigDecimal RENTAL_DEPOSIT_PRICE = Utils.convertDoubleToBigDecimal(700000);
	private final BigDecimal RENTAL_BASE_PRICE = Utils.convertDoubleToBigDecimal(15000);
	private final BigDecimal ADDTIONAL_PRICE_PER_INCREASE_VALUE = Utils.convertDoubleToBigDecimal(4500);

	private float batteryPercentage;
	private int loadCycles;
	private float estimatedUsageTimeRemaining;

	public float getBatteryPercentage() {
		return batteryPercentage;
	}

	public void setBatteryPercentage(float batteryPercentage) {
		this.batteryPercentage = batteryPercentage;
	}

	public int getLoadCycles() {
		return loadCycles;
	}

	public void setLoadCycles(int loadCycles) {
		this.loadCycles = loadCycles;
	}

	public float getEstimatedUsageTimeRemaining() {
		return estimatedUsageTimeRemaining;
	}

	public void setEstimatedUsageTimeRemaining(float estimatedUsageTimeRemaining) {
		this.estimatedUsageTimeRemaining = estimatedUsageTimeRemaining;
	}

	@Override
	public BigDecimal getRentalDepositPrice() {
		return this.RENTAL_DEPOSIT_PRICE;
	}

	public EBike() {
		super();
	}

	public EBike(String name, String licensePlate, String producer, String description) {
		super(name, licensePlate, producer, description);
	}

	public EBike(String id, String name, String licensePlate, String producer, String description) {
		super(id, name, licensePlate, producer, description);
	}

	public EBike(String name, float weight, String licensePlate, Date manufacturingDate, String producer,
			BigDecimal cost, String description, BikeStatus status) {
		super(name, weight, licensePlate, manufacturingDate, producer, cost, description, status);
	}

	public EBike(String id, String name, float weight, String licensePlate, Date manufacturingDate, String producer,
			BigDecimal cost, String description, BikeStatus status) {
		super(id, name, weight, licensePlate, manufacturingDate, producer, cost, description, status);
	}

	public EBike(String name, float weight, String licensePlate, Date manufacturingDate, String producer,
			BigDecimal cost, String description, BikeStatus status, float batteryPercentage, int loadCycles,
			float estimatedUsageTimeRemaining) {
		super(name, weight, licensePlate, manufacturingDate, producer, cost, description, status);
		this.batteryPercentage = batteryPercentage;
		this.loadCycles = loadCycles;
		this.estimatedUsageTimeRemaining = estimatedUsageTimeRemaining;
	}

	public EBike(String id, String name, float weight, String licensePlate, Date manufacturingDate, String producer,
			BigDecimal cost, String description, BikeStatus status, float batteryPercentage, int loadCycles,
			float estimatedUsageTimeRemaining) {
		super(id, name, weight, licensePlate, manufacturingDate, producer, cost, description, status);
		this.batteryPercentage = batteryPercentage;
		this.loadCycles = loadCycles;
		this.estimatedUsageTimeRemaining = estimatedUsageTimeRemaining;
	}

	public String formatBatteryInfo() {
		return String.format("Remain %f%%, last for %f minutes", this.loadCycles, this.estimatedUsageTimeRemaining);
	}

	@Override
	public String toString() {
		return super.toString() + "- " + this.loadCycles + " charged times";
	}

	@Override
	public boolean match(Bike bike) {
		if (bike == null)
			return true;

		boolean res = super.match(bike);

		if (!res)
			return false;

		if (!(bike instanceof EBike))
			return false;
		EBike ebike = (EBike) bike;

		if (ebike.batteryPercentage != 0 && this.batteryPercentage != ebike.batteryPercentage)
			return false;

		if (ebike.loadCycles != 0 && this.loadCycles != ebike.loadCycles)
			return false;

		if (ebike.estimatedUsageTimeRemaining != 0
				&& this.estimatedUsageTimeRemaining != ebike.estimatedUsageTimeRemaining)
			return false;

		return true;
	}

	@Override
	public BigDecimal calculateRentPrice(long totalCalculationRentValue) {
		return super.calculateRentPrice(this.RENTAL_BASE_PRICE, this.ADDTIONAL_PRICE_PER_INCREASE_VALUE,
				totalCalculationRentValue);
	}
}