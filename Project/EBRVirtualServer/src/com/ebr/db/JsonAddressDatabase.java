package com.ebr.db;

import java.util.ArrayList;

// beans
import com.ebr.bean.Address;

// db interfaces
import com.ebr.db.interfaces.IAddressDatabase;

// db seed
import com.ebr.db.seed.Seed;

// utils
import com.ebr.utils.Utils;

public class JsonAddressDatabase implements IAddressDatabase {
	private static IAddressDatabase singleton = new JsonAddressDatabase();

	private ArrayList<Address> addresses = Seed.singleton().getAddresses();

	private JsonAddressDatabase() {
	}

	public static IAddressDatabase singleton() {
		return singleton;
	}

	@Override
	public ArrayList<Address> search(Address address) {
		ArrayList<Address> res = new ArrayList<Address>();
		for (Address a : addresses) {
			if (a.match(address)) {
				res.add(a);
			}
		}
		return res;
	}

	@Override
	public Address getById(String addressId) {
		if (addressId != null) {
			for (Address a : addresses) {
				if (a.getId().equals(addressId)) {
					return a;
				}
			}
		}

		return null;
	}

	@Override
	public Address add(Address address) {
		for (Address a : addresses) {
			if (a.equals(address)) {
				return null;
			}
		}

		if (address.getId() == null || address.getId().equals(""))
			address.setId(Utils.generateUUID());

		addresses.add(address);

		return address;
	}

	@Override
	public Address update(Address address) {
		for (Address a : addresses) {
			if (a.equals(address)) {
				addresses.remove(a);
				addresses.add(address);
				return address;
			}
		}

		return null;
	}

	@Override
	public boolean checkExistById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean removeById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
