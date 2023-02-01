package com.ebr.bean.options;

import com.ebr.enums.BikeStatus;

public class SubdivisionOption {
	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public SubdivisionOption() {
		super();
	}

	public SubdivisionOption(String key) {
		super();
		this.key = key;
	}

	public SubdivisionOption(String key, String value) {
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

		return ((SubdivisionOption) obj).getKey().equals(this.key);
	}
}
