package com.ebr.serverapi.test.bike;

import org.junit.Test;

import com.ebr.bean.bikes.EBike;
import com.ebr.bean.bikes.TwinBike;
import com.ebr.enums.BikeStatus;
import com.ebr.serverapi.bikes.EBikeApi;
import com.ebr.serverapi.bikes.TwinBikeApi;
import com.ebr.serverapi.bikes.TwinBikeApi;
import com.ebr.utils.Utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TwinBikeApiTest {
	private TwinBikeApi api = new TwinBikeApi();

	@Test
	public void testGetAllTwinBikes() {
		ArrayList<TwinBike> list = api.getTwinBikes(null);
		assertEquals("Error in getTwinBikes API!", list.size(), 3);
	}

	@Test
	public void testGetAllTwinBikesWithParamId() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "59c0e19f-1cdd-4c31-b720-e4495a96f206");
		ArrayList<TwinBike> list = api.getTwinBikes(params);
		assertTrue("Error in testGetAllTwinBikesWithParamId API!", list.size() == 1);
	}

	@Test
	public void testGetAllTwinBikesWithParamName() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", "Twin bike 01");
		ArrayList<TwinBike> list = api.getTwinBikes(params);
		assertTrue("Error in testGetAllTwinBikesWithParamName API!", list.size() == 1);
	}

	@Test
	public void testGetAllTwinBikesWithParamLicensePlate() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("licensePlate", "29-TB-59c0e19f");
		ArrayList<TwinBike> list = api.getTwinBikes(params);
		assertTrue("Error in testGetAllTwinBikesWithParamLicensePlate API!", list.size() == 1);
	}

	@Test
	public void testGetAllTwinBikesWithParamProducer() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("producer", "Rose Hips factory");
		ArrayList<TwinBike> list = api.getTwinBikes(params);
		assertTrue("Error in testGetAllTwinBikesWithParamProducer API!", list.size() == 3);
	}

	@Test
	public void testGetAllTwinBikesWithParamDescription() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("description", "a good description");
		ArrayList<TwinBike> list = api.getTwinBikes(params);
		assertTrue("Error in getAllTwinBikesWithParams API!", list.size() == 0);
	}

	@Test(timeout = 1000)
	public void testResponse() {
		api.getTwinBikes(null);
	}

	@Test
	public void testAddTwinBike() {
		TwinBike twinBike = new TwinBike("Name test", (float) 3.3, "License plate test", new Date(), "Producer test",
				Utils.convertDoubleToBigDecimal(1000000), "Description test", BikeStatus.AVAILABLE);

		api.addTwinBike(twinBike);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", "Name test");
		ArrayList<TwinBike> list = api.getTwinBikes(params);
		assertTrue("Error in addTwinBike API!", list.size() > 0);

		TwinBike newTwinBike = api.getTwinBikes(params).get(0);
		twinBike.setId(newTwinBike.getId());
		assertEquals("Error in updateTwinBike API!", newTwinBike, twinBike);
	}

	@Test
	public void testUpdateTwinBike() {
		ArrayList<TwinBike> list = api.getTwinBikes(null);
		assertTrue("No data", list.size() > 0);

		TwinBike twinBike = list.get(0);
		String newDescription = "New description set by test";
		twinBike.setDescription(newDescription);
		api.updateTwinBike(twinBike);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", twinBike.getId());
		list = api.getTwinBikes(params);
		assertTrue("Error in updateTwinBike API!", list.size() > 0);

		TwinBike newTwinBike = api.getTwinBikes(params).get(0);
		assertEquals("Error in updateTwinBike API!", newTwinBike.getDescription(), newDescription);
	}
}