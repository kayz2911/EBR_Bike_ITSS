package com.ebr.bean.options;

import com.ebr.enums.BikeStatus;

public class BikeStatusOption {
	private BikeStatus key;
	private String value;

	public BikeStatus getKey() {
		return key;
	}

	public void setKey(BikeStatus key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public BikeStatusOption() {
		super();
	}

	public BikeStatusOption(BikeStatus key) {
		super();
		this.key = key;
		this.value = key.getStatusStr();
	}

	public BikeStatusOption(BikeStatus key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		return ((BikeStatusOption) obj).getKey().equals(this.key);
	}
}
