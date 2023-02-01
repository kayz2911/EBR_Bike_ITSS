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

public class ProvinceApiTest {
	private SubdivisionApi api = new SubdivisionApi();

	@Test
	public void testGetAllProvinces() {
		ArrayList<Subdivision> list = api.getProvinces(null);
		assertEquals("Error in getBikeStations API!", list.size(), 63);
	}

	@Test
	public void testGetAllProvincesWithParamsCode() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("code", "01");
		ArrayList<Subdivision> list = api.getProvinces(params);
		assertEquals("Error in testGetAllProvincesWithParamsCode API!", list.size(), 1);
	}

	@Test
	public void testGetAllProvincesWithParamsName() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", "Thành phố Hà Nội");
		ArrayList<Subdivision> list = api.getProvinces(params);
		assertEquals("Error in testGetAllProvincesWithParamsName API!", list.size(), 1);
	}

	@Test(timeout = 2000)
	public void testResponse() {
		api.getProvinces(null);
	}
}