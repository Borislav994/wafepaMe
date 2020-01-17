package jwd.wafepaMe.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepaMe.model.Address;
import jwd.wafepaMe.service.AddressService;
import jwd.wafepaMe.web.dto.AddressDTO;

@Component
public class AddressDTOtoAddress implements Converter<AddressDTO, Address> {

	@Autowired
	AddressService addressService;

	@Override
	public Address convert(AddressDTO dto) {
		Address address = new Address();

		if (dto.getId() != null) {
			address = addressService.findOne(dto.getId());

			if (address == null) {
				throw new IllegalStateException("Tried to " + "modify a non-existant address");
			}
		}

		address.setId(dto.getId());
		address.setStreet(dto.getStreat());
		address.setNumber(dto.getNumber());

		return address;
	}

	public List<Address> convert(List<AddressDTO> dtoAddresses) {
		List<Address> addresses = new ArrayList<>();

		for (AddressDTO dto : dtoAddresses) {
			addresses.add(convert(dto));
		}

		return addresses;
	}
}
