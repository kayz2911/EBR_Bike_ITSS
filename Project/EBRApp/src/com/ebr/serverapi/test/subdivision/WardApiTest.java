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

public class WardApiTest {
	private SubdivisionApi api = new SubdivisionApi();

	@Test
	public void testGetAllWards() {
		ArrayList<Subdivision> list = api.getWards(null);
		assertEquals("Error in getBikeStations API!", list.size(), 10603);
	}

	@Test
	public void testGetAllWardsWithParamsCode() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("code", "00001");
		ArrayList<Subdivision> list = api.getWards(params);
		assertEquals("Error in testGetAllWardsWithParamsCode API!", list.size(), 1);
	}

	@Test
	public void testGetAllWardsWithParamsName() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", "Phường Phúc Xá");
		ArrayList<Subdivision> list = api.getWards(params);
		assertEquals("Error in testGetAllWardsWithParamsName API!", list.size(), 1);
	}

	@Test(timeout = 2000)
	public void testResponse() {
		api.getWards(null);
	}
}