package com.ebr.bean.options;

import com.ebr.enums.BikeStatus;

public class WardOption extends SubdivisionOption {
	private String wardCode;

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public WardOption() {
		super();
	}

	public WardOption(String key) {
		super(key);
	}

	public WardOption(String key, String value, String wardCode) {
		super(key, value);
		this.wardCode = wardCode;
	}
}
