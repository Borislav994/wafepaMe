package jwd.wafepaMe.service;

import java.util.List;

import jwd.wafepaMe.model.Address;

public interface AddressService {
	
	Address findOne(Long id);
	
	List<Address> findAll();
	
	Address save(Address address);
	
	void delete(Long id);

	List<Address> findByUser(Long userId);

}
