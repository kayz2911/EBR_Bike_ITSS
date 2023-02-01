package com.ebr.serverapi.test.bike;

import org.junit.Test;

import com.ebr.bean.bikes.EBike;
import com.ebr.bean.bikes.EBike;
import com.ebr.enums.BikeStatus;
import com.ebr.serverapi.bikes.EBikeApi;
import com.ebr.utils.Utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class EBikeApiTest {
	private EBikeApi api = new EBikeApi();

	@Test
	public void testGetAllEBikes() {
		ArrayList<EBike> list = api.getEBikes(null);
		assertEquals("Error in getEBikes API!", list.size(), 7);
	}

	@Test
	public void testGetAllEBikesWithParamId() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "e8aaf6c5-be23-40c7-804b-ed7e9337dfe3");
		ArrayList<EBike> list = api.getEBikes(params);
		assertTrue("Error in testGetAllEBikesWithParamId API!", list.size() == 1);
	}

	@Test
	public void testGetAllEBikesWithParamName() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", "EBike 01");
		ArrayList<EBike> list = api.getEBikes(params);
		assertTrue("Error in testGetAllEBikesWithParamName API!", list.size() == 1);
	}

	@Test
	public void testGetAllEBikesWithParamLicensePlate() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("licensePlate", "29-EB-e8aaf6c5");
		ArrayList<EBike> list = api.getEBikes(params);
		assertTrue("Error in testGetAllEBikesWithParamLicensePlate API!", list.size() == 1);
	}

	@Test
	public void testGetAllEBikesWithParamProducer() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("producer", "Tesla Coorp");
		ArrayList<EBike> list = api.getEBikes(params);
		assertTrue("Error in testGetAllEBikesWithParamProducer API!", list.size() == 6);
	}

	@Test
	public void testGetAllEBikesWithParamDescription() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("description", "a good description");
		ArrayList<EBike> list = api.getEBikes(params);
		assertTrue("Error in getAllEBikesWithParams API!", list.size() == 0);
	}

	@Test(timeout = 1000)
	public void testResponse() {
		api.getEBikes(null);
	}

	@Test
	public void testAddEBike() {
		EBike ebike = new EBike("Name test", (float) 3.3, "License plate test", new Date(), "Producer test",
				Utils.convertDoubleToBigDecimal(1000000), "Description test", BikeStatus.AVAILABLE, (float) 90, 500,
				(float) 10);

		api.addEBike(ebike);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", "Name test");
		ArrayList<EBike> list = api.getEBikes(params);
		assertTrue("Error in addEBike API!", list.size() > 0);

		EBike newEBike = api.getEBikes(params).get(0);
		ebike.setId(newEBike.getId());
		assertEquals("Error in updateEBike API!", newEBike, ebike);
	}

	@Test
	public void testUpdateEBike() {
		ArrayList<EBike> list = api.getEBikes(null);
		assertTrue("No data", list.size() > 0);

		EBike ebike = list.get(0);
		String newDescription = "New description set by test";
		ebike.setDescription(newDescription);
		api.updateEBike(ebike);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", ebike.getId());
		list = api.getEBikes(params);
		assertTrue("Error in updateEBike API!", list.size() > 0);

		EBike newEBike = api.getEBikes(params).get(0);
		assertEquals("Error in updateEBike API!", newEBike.getDescription(), newDescription);
	}
}