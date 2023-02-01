package com.ebr.bean.bikestation;

import java.util.ArrayList;

// beans
import com.ebr.bean.Address;
import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.RegularBike;
import com.ebr.bean.bikes.EBike;
import com.ebr.bean.bikes.TwinBike;
import com.ebr.bean.subdivisions.Subdivision;

// utils
import com.ebr.utils.Utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BikeStation {
	private String id;
	private String name;
	private String addressId;
	private int numberOfDock;

	// display data
	private Address address;
	private int numberOfBikes;
	private int numberOfEBikes;
	private int numberOfTwinBikes;
	private int numberOfEmptyDock;
	private ArrayList<BikeDock> bikeDocks;
	private ArrayList<Bike> bikes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public int getNumberOfDock() {
		return numberOfDock;
	}

	public void setNumberOfDock(int numberOfDock) {
		this.numberOfDock = numberOfDock;
	}

	// display data
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getNumberOfBikes() {
		return numberOfBikes;
	}

	public int getNumberOfEBikes() {
		return numberOfEBikes;
	}

	public int getNumberOfTwinBikes() {
		return numberOfTwinBikes;
	}

	public int getNumberOfEmptyDock() {
		return this.numberOfEmptyDock;
	}

	public ArrayList<BikeDock> getBikeDocks() {
		return bikeDocks;
	}

	public void setBikeDocks(ArrayList<BikeDock> bikeDocks) {
		this.bikeDocks = bikeDocks;

		// set list bikes
		this.bikes = new ArrayList<Bike>();

		for (BikeDock dock : bikeDocks) {
			this.bikes.add(dock.getBike());
		}

		// count number of bike docks
		ArrayList<RegularBike> standardbikes = Utils.filterArrayListByType(this.bikes, RegularBike.class);
		this.numberOfBikes = standardbikes != null ? standardbikes.size() : 0;

		// count number of ebike docks
		ArrayList<EBike> ebikes = Utils.filterArrayListByType(this.bikes, EBike.class);
		this.numberOfEBikes = ebikes != null ? ebikes.size() : 0;

		// count number of twinbike docks
		ArrayList<TwinBike> twinbikes = Utils.filterArrayListByType(this.bikes, TwinBike.class);
		this.numberOfTwinBikes = ebikes != null ? ebikes.size() : 0;

		// count number of empty docks
		this.numberOfEmptyDock = this.numberOfDock - this.numberOfBikes - this.numberOfEBikes - this.numberOfTwinBikes;
	}

	public BikeStation() {
		super();
	}

	public BikeStation(String name, String addressId) {
		super();
		this.name = name;
		this.addressId = addressId;
	}

	public BikeStation(String name, int numberOfDock) {
		super();
		this.name = name;
		this.numberOfDock = numberOfDock;
	}

	public BikeStation(String id, String name, String addressId) {
		super();
		this.id = id;
		this.name = name;
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		return String.format("%s (%d/%d)", this.name, this.numberOfDock - this.numberOfEmptyDock, this.numberOfDock);
	}

	public boolean match(BikeStation bikeStation) {
		if (bikeStation == null)
			return true;

		if (bikeStation.id != null && !this.id.equals(bikeStation.id))
			return false;

		if (bikeStation.name != null && !bikeStation.name.equals("") && !this.name.contains(bikeStation.name))
			return false;

		if (bikeStation.addressId != null && !this.addressId.equals(bikeStation.addressId))
			return false;

		if (bikeStation.address != null && this.address != null) {
			if (bikeStation.address.getProvinceName() != null && !bikeStation.address.getProvinceName().equals("")
					&& !this.address.getProvinceName().contains(bikeStation.address.getProvinceName()))
				return false;

			if (bikeStation.address.getDistrictName() != null && !bikeStation.address.getDistrictName().equals("")
					&& !this.address.getDistrictName().contains(bikeStation.address.getDistrictName()))
				return false;

			if (bikeStation.address.getWardName() != null && !bikeStation.address.getWardName().equals("")
					&& !this.address.getWardName().contains(bikeStation.address.getWardName()))
				return false;

			if (bikeStation.address.getStreet() != null && !bikeStation.address.getStreet().equals("")
					&& !this.address.getStreet().contains(bikeStation.address.getStreet()))
				return false;
		}

		if (bikeStation.name != null && !bikeStation.name.equals("") && !this.name.contains(bikeStation.name))
			return false;

		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BikeStation)
			return this.id.equals(((BikeStation) obj).id);

		return false;
	}
}