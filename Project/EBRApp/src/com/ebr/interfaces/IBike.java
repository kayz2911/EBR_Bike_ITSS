package com.ebr.interfaces;

import java.math.BigDecimal;

public interface IBike {
	public BigDecimal calculateRentPrice(BigDecimal basePrice, BigDecimal additionalPricePerIncreaseRate,
			long totalRentValue);
}
