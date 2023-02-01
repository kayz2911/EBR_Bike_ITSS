package com.ebr.constants;

public final class Constants {
	private Constants() {
	}

	public static final class SystemSettings {
		private SystemSettings() {
		}

		public static final String CURRENCY_PREFIX = "";
		public static final String CURRENCY_SUFFIX = "VND";
	}

	public static final class PriceCalculation {
		private PriceCalculation() {
		}

		public static final String CALCULATION_UNIT = "minute";
		public static final String CALCULATION_UNIT_TYPE = "time";
		public static final long BASE_CALCULATION_RENT_VALUE = 30;
		public static final long PRICE_INCREASE_VALUE = 15;
	}
}
