package com.ebr.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderStatus {
    @JsonProperty("REQUESTING")
    REQUESTING("Requesting"), 	// place order -> requesting, waiting for approved, bike still in bike dock
    @JsonProperty("APPROVED") 
    APPROVED("Approved"),		// approved order -> bike is taken from bike dock -> remove bike dock
    @JsonProperty("REJECTED")
    REJECTED("Reject"),			// rejected order 
    @JsonProperty("SUCCEED")
    SUCCEED("Succeed");			// succeed order -> bike is returned into bike dock in any bike station
 
    private String orderStatus;
 
    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
 
    public String getStatusStr() {
        return orderStatus;
    }
}
