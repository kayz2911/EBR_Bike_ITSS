package com.ebr.serverapi.test.bikestation;

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
import com.ebr.bean.bikestation.BikeDock;
import com.ebr.enums.BikeStatus;
import com.ebr.serverapi.bikestation.BikeDockApi;
import com.ebr.utils.Utils;

public class BikeDockApiTest {
	private BikeDockApi api = new BikeDockApi();

	@Test
	public void testGetAllBikeDocks() {
		ArrayList<BikeDock> list = api.getBikeDocks(null);
		assertEquals("Error in getBikeDocks API!", list.size(), 9);
	}

	@Test
	public void testGetAllBikeDocksWithParamId() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "f48b900f-bf83-46a0-852b-3bbb229442a9");
		ArrayList<BikeDock> list = api.getBikeDocks(params);
		assertTrue("Error in testGetAllBikeDocksWithParamId API!", list.size() == 1);
	}

	@Test
	public void testGetAllBikeDocksWithParamBikeStationId() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bikeStationId", "7f7388f9-2d63-4ab2-b270-daaa0699e80e");
		ArrayList<BikeDock> list = api.getBikeDocks(params);
		assertTrue("Error in testGetAllBikeDocksWithParamBikeStationId API!", list.size() == 5);
	}

	@Test
	public void testGetAllBikeDocksWithParamBikeId() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bikeId", "262c91bd-ce00-4e62-aad6-dbc1bbfdcaa3");
		ArrayList<BikeDock> list = api.getBikeDocks(params);
		assertTrue("Error in testGetAllBikeDocksWithParamBikeId API!", list.size() == 1);
	}

	@Test(timeout = 1000)
	public void testResponse() {
		api.getBikeDocks(null);
	}

	@Test
	public void testAddBikeDock() {
		BikeDock bikeDock = new BikeDock("7f7388f9-2d63-4ab2-b270-daaa0699e80e",
				"0d8c32d9-926c-4144-928f-c2407ff3f96c");

		api.addBikeDock(bikeDock);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bikeId", "0d8c32d9-926c-4144-928f-c2407ff3f96c");
		ArrayList<BikeDock> list = api.getBikeDocks(params);
		assertTrue("Error in addBikeDock API!", list.size() > 0);

		BikeDock newBikeDock = api.getBikeDocks(params).get(0);
		assertEquals("Error in testAddBikeDock API!", newBikeDock.getBikeId(), bikeDock.getBikeId());
	}

	@Test
	public void testUpdateBikeDock() {
		ArrayList<BikeDock> list = api.getBikeDocks(null);
		assertTrue("No data", list.size() > 0);

		BikeDock bikeDock = list.get(0);
		String newBikeId = "491284ea-a12c-4e1f-9fc0-670235f67d58";
		bikeDock.setBikeId(newBikeId);
		api.updateBikeDock(bikeDock);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", bikeDock.getId());
		list = api.getBikeDocks(params);
		assertTrue("Error in updateBikeDock API!", list.size() > 0);

		BikeDock newBikeDock = api.getBikeDocks(params).get(0);
		assertEquals("Error in updateBikeDock API!", newBikeDock.getBikeId(), newBikeId);
	}
}