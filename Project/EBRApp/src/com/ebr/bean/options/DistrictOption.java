package com.ebr.bean.options;

import com.ebr.enums.BikeStatus;

public class DistrictOption extends SubdivisionOption {
	private String provinceCode;

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public DistrictOption() {
		super();
	}

	public DistrictOption(String key) {
		super(key);
	}

	public DistrictOption(String key, String value, String provinceCode) {
		super(key, value);
		this.provinceCode = provinceCode;
	}
}
