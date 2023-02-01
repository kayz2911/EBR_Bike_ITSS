package com.ebr.bean.subdivisions;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("subdivision")
@JsonSubTypes({ @Type(value = Province.class, name = "province"), @Type(value = District.class, name = "district"),
		@Type(value = Ward.class, name = "ward") })
public class Subdivision {
	private String code;
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subdivision() {
		super();
	}

	public Subdivision(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", this.code, this.name);
	}

	public boolean match(Subdivision subdivision) {
		if (subdivision == null)
			return true;

		if (subdivision.code != null && !this.code.equals(subdivision.code))
			return false;

		if (subdivision.name != null && !subdivision.name.equals("") && !this.name.contains(subdivision.name))
			return false;

		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Subdivision)
			return this.code.equals(((Subdivision) obj).code);

		return false;
	}
}