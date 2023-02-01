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
import com.ebr.bean.bikestation.BikeStation;
import com.ebr.enums.BikeStatus;
import com.ebr.serverapi.bikestation.BikeStationApi;
import com.ebr.utils.Utils;

public class BikeStationApiTest {
	private BikeStationApi api = new BikeStationApi();

	@Test
	public void testGetAllBikeStations() {
		ArrayList<BikeStation> list = api.getBikeStations(null);
		assertEquals("Error in getBikeStations API!", list.size(), 11);
	}

	@Test
	public void testGetAllBikeStationsWithParamId() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "7f7388f9-2d63-4ab2-b270-daaa0699e80e");
		ArrayList<BikeStation> list = api.getBikeStations(params);
		assertTrue("Error in testGetAllBikeStationsWithParamId API!", list.size() == 1);
	}

	@Test
	public void testGetAllBikeStationsWithParamName() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", "Bike station 01");
		ArrayList<BikeStation> list = api.getBikeStations(params);
		assertTrue("Error in testGetAllBikeStationsWithParamName API!", list.size() == 1);
	}

	@Test
	public void testGetAllBikeStationsWithParamAddressId() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("addressId", "3d262e73-cf1b-45c2-bebe-9c0fc1a89cf7");
		ArrayList<BikeStation> list = api.getBikeStations(params);
		assertTrue("Error in testGetAllBikeStationsWithParamAddressId API!", list.size() == 1);
	}

	@Test
	public void testGetAllBikeStationsWithParamAddressLocation() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("location", "1 Giải Phóng");
		ArrayList<BikeStation> list = api.getBikeStations(params);
		assertTrue("Error in testGetAllBikeStationsWithParamAddressLocation API!", list.size() == 1);
	}

	@Test(timeout = 1000)
	public void testResponse() {
		api.getBikeStations(null);
	}

	@Test
	public void testAddBikeStation() {
		BikeStation bikeStation = new BikeStation("Name test", 1000);

		Address address = new Address("01", "006", "00220", "256 Xa Dan", "21.0126121", "105.8337852");

		bikeStation.setAddress(address);

		api.addBikeStation(bikeStation);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", "Name test");
		ArrayList<BikeStation> list = api.getBikeStations(params);
		assertTrue("Error in addBikeStation API!", list.size() > 0);

		BikeStation newBikeStation = api.getBikeStations(params).get(0);
		assertEquals("Error in addBikeStation API!", newBikeStation.getName(), bikeStation.getName());
		assertEquals("Error in addBikeStation API!", newBikeStation.getNumberOfDock(),
				bikeStation.getNumberOfDock());
		assertEquals("Error in addBikeStation API!", newBikeStation.getAddress().getProvinceCode(),
				bikeStation.getAddress().getProvinceCode());
		assertEquals("Error in addBikeStation API!", newBikeStation.getAddress().getDistrictCode(),
				bikeStation.getAddress().getDistrictCode());
		assertEquals("Error in addBikeStation API!", newBikeStation.getAddress().getWardCode(),
				bikeStation.getAddress().getWardCode());
		assertEquals("Error in addBikeStation API!", newBikeStation.getAddress().getStreet(),
				bikeStation.getAddress().getStreet());
		assertEquals("Error in addBikeStation API!", newBikeStation.getAddress().getGPSXCoordinate(),
				bikeStation.getAddress().getGPSXCoordinate());
		assertEquals("Error in addBikeStation API!", newBikeStation.getAddress().getGPSYCoordinate(),
				bikeStation.getAddress().getGPSYCoordinate());
	}

	@Test
	public void testUpdateBikeStation() {
		ArrayList<BikeStation> list = api.getBikeStations(null);
		assertTrue("No data", list.size() > 0);

		BikeStation bikeStation = list.get(0);
		int newNumberOfDock = 10000;
		bikeStation.setNumberOfDock(newNumberOfDock);
		api.updateBikeStation(bikeStation);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", bikeStation.getId());
		list = api.getBikeStations(params);
		assertTrue("Error in updateBikeStation API!", list.size() > 0);

		BikeStation newBikeStation = api.getBikeStations(params).get(0);
		assertEquals("Error in updateBikeStation API!", newBikeStation.getNumberOfDock(), newNumberOfDock);
	}
}