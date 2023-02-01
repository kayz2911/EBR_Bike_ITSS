package com.ebr.serverapi.test.bike;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ RegularBikeApiTest.class, EBikeApiTest.class, TwinBikeApiTest.class })
public class BikeApiTests {

}