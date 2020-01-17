package jwd.wafepaMe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepaMe.model.Address;
import jwd.wafepaMe.repository.AddressRepositiry;
import jwd.wafepaMe.service.AddressService;

@Service
public class JpaAddressService implements AddressService {
	
	@Autowired
	private AddressRepositiry addressRepository;


	@Override
	public Address findOne(Long id) {
		// TODO Auto-generated method stub
		return addressRepository.findOne(id);
	}

	@Override
	public List<Address> findAll() {
		// TODO Auto-generated method stub
		return addressRepository.findAll();
	}

	@Override
	public Address save(Address address) {
		// TODO Auto-generated method stub
		return addressRepository.save(address);
	}

	@Override
	public void delete(Long id) {
		addressRepository.delete(id);
	}

	@Override
	public List<Address> findByUser(Long userId) {
		// TODO Auto-generated method stub
		return addressRepository.findByUserId(userId);
	}
	
	

}
