package com.ebr.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
	private String id;
	private String provinceCode;
	private String districtCode;
	private String wardCode;
	private String street;
	@JsonProperty("GPSXCoordinate")
	private String GPSXCoordinate;
	@JsonProperty("GPSYCoordinate")
	private String GPSYCoordinate;

	// display data
	private String provinceName;
	private String districtName;
	private String wardName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getGPSXCoordinate() {
		return GPSXCoordinate;
	}

	public void setGPSXCoordinate(String gPSXCoordinate) {
		GPSXCoordinate = gPSXCoordinate;
	}

	public String getGPSYCoordinate() {
		return GPSYCoordinate;
	}

	public void setGPSYCoordinate(String gPSYCoordinate) {
		GPSYCoordinate = gPSYCoordinate;
	}

	// display data
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public Address() {
		super();
	}

	public Address(String provinceCode, String districtCode, String wardCode, String street, String GPSXCoordinate,
			String GPSYCoordinate) {
		super();
		this.provinceCode = provinceCode;
		this.districtCode = districtCode;
		this.wardCode = wardCode;
		this.street = street;
		this.GPSXCoordinate = GPSXCoordinate;
		this.GPSYCoordinate = GPSYCoordinate;
	}

	public Address(String id, String provinceCode, String districtCode, String wardCode, String street,
			String GPSXCoordinate,
			String GPSYCoordinate) {
		super();
		this.id = id;
		this.provinceCode = provinceCode;
		this.districtCode = districtCode;
		this.wardCode = wardCode;
		this.street = street;
		this.GPSXCoordinate = GPSXCoordinate;
		this.GPSYCoordinate = GPSYCoordinate;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s", this.street, this.wardName, this.districtName, this.provinceName);
	}

	public boolean match(Address address) {
		if (address == null)
			return true;

		if (address.id != null && !this.id.equals(address.id))
			return false;

		if (address.provinceCode != null && !this.provinceCode.equals(address.provinceCode))
			return false;

		if (address.districtCode != null && !this.districtCode.equals(address.districtCode))
			return false;

		if (address.wardCode != null && !this.wardCode.equals(address.wardCode))
			return false;

		if (address.street != null && !address.street.equals("") && !this.street.contains(address.street))
			return false;

		if (address.GPSXCoordinate != null && !this.GPSXCoordinate.equals(address.GPSXCoordinate))
			return false;

		if (address.GPSYCoordinate != null && !this.GPSYCoordinate.equals(address.GPSYCoordinate))
			return false;

		if (address.provinceName != null && !address.provinceName.equals("")
				&& !this.provinceName.contains(address.provinceName))
			return false;

		if (address.districtName != null && !address.districtName.equals("")
				&& !this.districtName.contains(address.districtName))
			return false;

		if (address.wardName != null && !address.wardName.equals("") && !this.wardName.contains(address.wardName))
			return false;

		return true;
	}
}