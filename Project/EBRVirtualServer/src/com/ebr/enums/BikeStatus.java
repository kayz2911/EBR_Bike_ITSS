package com.ebr.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BikeStatus {
    @JsonProperty("AVAILABLE")
	AVAILABLE("Available"),
    @JsonProperty("HOLDING")
    HOLDING("Holding"),
    @JsonProperty("RENTED")
	RENTED("Rented");
 
    private String bikeStatus;
 
    BikeStatus(String bikeStatus) {
        this.bikeStatus = bikeStatus;
    }
 
    public String getStatusStr() {
        return bikeStatus;
    }
}
