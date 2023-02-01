package com.ebr.bean.bikestation;

// beans
import com.ebr.bean.bikes.Bike;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BikeDock {
	private String id;
	private String bikeStationId;
	private String bikeId;

	// display data
	private Bike bike;
	private BikeStation bikeStation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBikeStationId() {
		return bikeStationId;
	}

	public void setBikeStationId(String bikeStationId) {
		this.bikeStationId = bikeStationId;
	}

	public String getBikeId() {
		return bikeId;
	}

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public BikeStation getBikeStation() {
		return bikeStation;
	}

	public void setBikeStation(BikeStation bikeStation) {
		this.bikeStation = bikeStation;
	}

	public BikeDock() {
		super();
	}

	public BikeDock(String id, String bikeStationId, String bikeId) {
		super();
		this.id = id;
		this.bikeStationId = bikeStationId;
		this.bikeId = bikeId;
	}

	public BikeDock(String bikeStationId, String bikeId) {
		super();
		this.bikeStationId = bikeStationId;
		this.bikeId = bikeId;
	}

	@Override
	public String toString() {
		return String.format("id: %s - bikeStationId: %s - bikeId: %s", this.id, this.bikeStationId, this.bikeId);
	}

	public boolean match(BikeDock bikeDock) {
		if (bikeDock == null)
			return true;

		if (bikeDock.id != null && !this.id.equals(bikeDock.id))
			return false;

		if (bikeDock.bikeStationId != null && !this.bikeStationId.equals(bikeDock.bikeStationId))
			return false;

		if (bikeDock.bikeId != null && !this.bikeId.equals(bikeDock.bikeId))
			return false;

		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BikeDock)
			return this.id.equals(((BikeDock) obj).id);

		return false;
	}
}