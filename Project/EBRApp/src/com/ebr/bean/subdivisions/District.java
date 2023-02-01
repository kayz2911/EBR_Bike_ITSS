package com.ebr.bean.subdivisions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class District extends Subdivision {
	private String provinceCode;

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public District() {
		super();
	}

	public District(String code, String name) {
		super(code, name);
	}

	public District(String code, String name, String provinceCode) {
		super(code, name);
		this.provinceCode = provinceCode;
	}

	@Override
	public boolean match(Subdivision subdivision) {
		if (subdivision == null)
			return true;

		boolean res = super.match(subdivision);
		if (!res)
			return false;

		if (!(subdivision instanceof District))
			return false;
		District district = (District) subdivision;

		if (district.provinceCode != null) {
			if (this.provinceCode.equals(district.provinceCode))
				return true;

			return false;
		}

		return true;
	}
}