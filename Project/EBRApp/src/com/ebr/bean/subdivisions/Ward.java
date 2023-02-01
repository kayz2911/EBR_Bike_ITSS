package com.ebr.bean.subdivisions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ward extends Subdivision {
	private String districtCode;

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public Ward() {
		super();
	}

	public Ward(String code, String name) {
		super(code, name);
	}

	public Ward(String code, String name, String districtCode) {
		super(code, name);
		this.districtCode = districtCode;
	}

	@Override
	public boolean match(Subdivision subdivision) {
		if (subdivision == null)
			return true;

		boolean res = super.match(subdivision);
		if (!res)
			return false;

		if (!(subdivision instanceof Ward))
			return false;
		Ward ward = (Ward) subdivision;

		if (ward.districtCode != null) {
			if (this.districtCode.equals(ward.districtCode))
				return true;

			return false;
		}

		return true;
	}
}