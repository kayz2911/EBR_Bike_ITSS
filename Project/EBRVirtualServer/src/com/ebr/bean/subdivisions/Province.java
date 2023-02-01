package com.ebr.bean.subdivisions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Province extends Subdivision {
	public Province() {
		super();
	}

	public Province(String code, String name) {
		super(code, name);
	}

	@Override
	public boolean match(Subdivision subdivision) {
		if (subdivision == null)
			return true;

		boolean res = super.match(subdivision);
		if (!res)
			return false;

		if (!(subdivision instanceof Province))
			return false;

		return true;
	}
}