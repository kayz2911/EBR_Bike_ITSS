package com.ebr.db.seed;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

// beans
import com.ebr.bean.Address;
import com.ebr.bean.subdivisions.Subdivision;
import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikestation.BikeStation;
import com.ebr.bean.bikestation.BikeDock;
import com.ebr.bean.customer.Card;
import com.ebr.bean.customer.Customer;
import com.ebr.bean.order.Order;
import com.ebr.bean.bank.BankAccount;

public class Seed {
	private ArrayList<Subdivision> subdivisions;
	private ArrayList<Bike> bikes;
	private ArrayList<BikeStation> bikeStations;
	private ArrayList<BikeDock> bikeDocks;
	private ArrayList<Address> addresses;
	private ArrayList<Customer> customers;
	private ArrayList<Card> cards;
	private ArrayList<Order> orders;
	private ArrayList<BankAccount> bankAccounts;

	private final String[] subdivisionsSourceFiles = { "provinces", "districts", "wards" };
	private final String[] bikesSourceFiles = { "regularbikes", "ebikes", "twinbikes" };
	private final String[] bikeStationsSourceFiles = { "bikestations" };
	private final String[] bikeDocksSourceFiles = { "bikedocks" };
	private final String[] addressesSourceFiles = { "addresses" };
	private final String[] customersSourceFiles = { "customers" };
	private final String[] cardsSourceFiles = { "cards" };
	private final String[] ordersSourceFiles = { "orders" };
	private final String[] bankAccountsSourceFiles = { "bankAccounts" };

	private static Seed singleton = new Seed();

	private Seed() {
		start();
	}

	public static Seed singleton() {
		return singleton;
	}

	private void start() {
		// initialize
		subdivisions = new ArrayList<Subdivision>();
		bikes = new ArrayList<Bike>();
		bikeStations = new ArrayList<BikeStation>();
		bikeDocks = new ArrayList<BikeDock>();
		addresses = new ArrayList<Address>();
		customers = new ArrayList<Customer>();
		cards = new ArrayList<Card>();
		orders = new ArrayList<Order>();
		bankAccounts = new ArrayList<BankAccount>();

		// seed subdivisions
		seedDatasFromFiles(subdivisions, subdivisionsSourceFiles, Subdivision.class);

		// seed bikes
		seedDatasFromFiles(bikes, bikesSourceFiles, Bike.class);

		// seed bike stations
		seedDatasFromFiles(bikeStations, bikeStationsSourceFiles, BikeStation.class);

		// seed bike docks
		seedDatasFromFiles(bikeDocks, bikeDocksSourceFiles, BikeDock.class);

		// seed addresses
		seedDatasFromFiles(addresses, addressesSourceFiles, Address.class);

		// seed customers
		seedDatasFromFiles(customers, customersSourceFiles, Customer.class);

		// seed cards
		seedDatasFromFiles(cards, cardsSourceFiles, Card.class);

		// seed orders
		seedDatasFromFiles(orders, ordersSourceFiles, Order.class);

		// seed bank accounts
		seedDatasFromFiles(bankAccounts, bankAccountsSourceFiles, BankAccount.class);
	}

	public ArrayList<Subdivision> getSubdivisions() {
		return subdivisions;
	}

	public ArrayList<Bike> getBikes() {
		return bikes;
	}

	public ArrayList<BikeStation> getBikeStations() {
		return bikeStations;
	}

	public ArrayList<BikeDock> getBikeDocks() {
		return bikeDocks;
	}

	public ArrayList<Address> getAddresses() {
		return addresses;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public ArrayList<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public static void main(String[] args) {
		Seed seed = new Seed();
		seed.start();
	}

	// pragma region PRIVATE METHODS
	/**
	 * seed datas from files
	 * 
	 * @param {ArrayList<T>} datas
	 * @param {String[]}     sourceFiles
	 * @param {Class<T>}     cls
	 * 
	 */
	private <T> void seedDatasFromFiles(ArrayList<T> datas, String[] sourceFiles, Class<T> cls) {
		for (String fileName : sourceFiles) {
			try {
				datas.addAll(generateDataFromFile(
						new File(getClass().getResource(String.format("data/%s.json", fileName)).getPath())
								.getAbsolutePath().toString(),
						cls));
			} catch (Exception e) {
				System.out.println(String.format("Unable to resolve path: data/%s.json", fileName));
			}
		}
	}

	/**
	 * generate data from file
	 * 
	 * @param {String}   filePath
	 * @param {Class<T>} cls
	 * 
	 */
	private <T> ArrayList<? extends T> generateDataFromFile(String filePath, Class<T> cls) {
		ArrayList<? extends T> data = new ArrayList<T>();
		ObjectMapper mapper = new ObjectMapper();
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, cls);

		String json = FileReader.read(filePath);
		try {
			mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
			data = mapper.readValue(json, listType);
		} catch (IOException e) {
			// e.printStackTrace();
			System.out.println("Invalid JSON input data from " + filePath);
			System.out.println(e.getMessage());
		}

		return data;
	}
	// pragma endregion
}
