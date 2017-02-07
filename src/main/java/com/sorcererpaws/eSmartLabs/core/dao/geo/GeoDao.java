package com.sorcererpaws.eSmartLabs.core.dao.geo;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.common.Address;

public interface GeoDao {

	public Address createAddress(Address address);
	public Address updateAddress(Address address);
	public Address getAddress(long addressId);
	public boolean deleteAddress(long addressId);
	public List<Address> addresses();
}
