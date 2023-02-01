package com.ebr.serverapi.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ebr.serverapi.test.bike.EBikeApiTest;
import com.ebr.serverapi.test.bike.RegularBikeApiTest;
import com.ebr.serverapi.test.bike.TwinBikeApiTest;
import com.ebr.serverapi.test.bikestation.BikeDockApiTest;
import com.ebr.serverapi.test.bikestation.BikeStationApiTest;
import com.ebr.serverapi.test.customer.CardAPITest;
import com.ebr.serverapi.test.customer.CustomerAPITest;
import com.ebr.serverapi.test.order.OrderApiTest;
import com.ebr.serverapi.test.subdivision.ProvinceApiTest;
import com.ebr.serverapi.test.subdivision.DistrictApiTest;
import com.ebr.serverapi.test.subdivision.WardApiTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ RegularBikeApiTest.class, EBikeApiTest.class, TwinBikeApiTest.class, BikeStationApiTest.class,
		BikeDockApiTest.class, ProvinceApiTest.class, DistrictApiTest.class, WardApiTest.class, CardAPITest.class,
		CustomerAPITest.class, OrderApiTest.class })
public class ServerApiTests {

}