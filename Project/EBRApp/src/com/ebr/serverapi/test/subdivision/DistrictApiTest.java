package com.ebr.serverapi.test.subdivision;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ebr.bean.Address;
import com.ebr.bean.bikes.TwinBike;
import com.ebr.bean.bikestation.BikeStation;
import com.ebr.bean.subdivisions.Subdivision;
import com.ebr.enums.BikeStatus;
import com.ebr.serverapi.subdivision.SubdivisionApi;
import com.ebr.utils.Utils;

public class DistrictApiTest {
	private SubdivisionApi api = new SubdivisionApi();

	@Test
	public void testGetAllDistricts() {
		ArrayList<Subdivision> list = api.getDistricts(null);
		assertEquals("Error in getBikeStations API!", list.size(), 705);
	}

	@Test
	public void testGetAllDistrictsWithParamsCode() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("code", "001");
		ArrayList<Subdivision> list = api.getDistricts(params);
		assertEquals("Error in testGetAllDistrictsWithParamsCode API!", list.size(), 1);
	}

	@Test
	public void testGetAllDistrictsWithParamsName() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", "Quận Ba Đình");
		ArrayList<Subdivision> list = api.getDistricts(params);
		assertEquals("Error in testGetAllDistrictsWithParamsName API!", list.size(), 1);
	}

	@Test(timeout = 2000)
	public void testResponse() {
		api.getDistricts(null);
	}
}