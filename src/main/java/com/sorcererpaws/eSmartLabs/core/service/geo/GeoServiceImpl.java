package com.sorcererpaws.eSmartLabs.core.service.geo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sorcererpaws.eSmartLabs.core.dao.geo.GeoDao;
import com.sorcererpaws.eSmartLabs.core.entity.common.Address;

@Service
@Transactional(readOnly = true)
public class GeoServiceImpl implements GeoService {

	@Autowired
	private GeoDao geoDao;

	@Override
	@Transactional(readOnly = false)
	public Address updateAddress(Address address) {
		return (address.getId() >= 0 ? (address.getId() == 0 ? getGeoDao().createAddress(address) : getGeoDao().updateAddress(address)) : null);
	}

	@Override
	public Address getAddress(long addressId) {
		return (addressId > 0 ? getGeoDao().getAddress(addressId) : null);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteAddress(long addressId) {
		return (addressId > 0 ? getGeoDao().deleteAddress(addressId) : false);
	}

	@Override
	public List<Address> addresses() {
		return getGeoDao().addresses();
	}

	//Getters and setters
	public GeoDao getGeoDao() {
		return geoDao;
	}

	public void setGeoDao(GeoDao geoDao) {
		this.geoDao = geoDao;
	}
}
