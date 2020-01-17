package jwd.wafepaMe.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jwd.wafepaMe.model.Address;
import jwd.wafepaMe.model.User;
import jwd.wafepaMe.service.AddressService;
import jwd.wafepaMe.service.UserService;
import jwd.wafepaMe.support.AddressDTOtoAddress;
import jwd.wafepaMe.support.AddressToAddressDTO;
import jwd.wafepaMe.web.dto.AddressDTO;

@Controller
@RequestMapping("/api/users/{userId}/addresses")
public class ApiAddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private UserService userService;

	@Autowired
	private AddressDTOtoAddress toAddress;

	@Autowired
	private AddressToAddressDTO toDTO;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AddressDTO>> get(@PathVariable Long userId) {
		List<Address> addresses = addressService.findByUser(userId);

		return new ResponseEntity<>(toDTO.convert(addresses), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AddressDTO> get(@PathVariable Long id, @PathVariable Long userId) {

		Address address = addressService.findOne(id);

		if (!userId.equals(address.getUser().getId())) {
			return new ResponseEntity<AddressDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(address), HttpStatus.OK);
	}
	
	@RequestMapping(
			method=RequestMethod.POST,
			consumes="application/json")
	public ResponseEntity<AddressDTO> add(
			@RequestBody AddressDTO newAddress,
			@PathVariable Long userId){
		
		User user = userService.findOne(userId);
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Address persisted = addressService.save(
				toAddress.convert(newAddress));
		
		user.addAddress(persisted);
		
		addressService.save(persisted);
		userService.save(user);
		
		
		return new ResponseEntity<>(
				toDTO.convert(persisted), 
				HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value="/{id}",
			method=RequestMethod.PUT,
			consumes="application/json")
	public ResponseEntity<AddressDTO> edit(
		@PathVariable Long id,
		@RequestBody AddressDTO editedAddress){
	
		if(id==null || !id.equals(editedAddress.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Address persisted = addressService.save(toAddress.convert(editedAddress));
		
		return new ResponseEntity<>(
				toDTO.convert(persisted), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<AddressDTO> delete(
			@PathVariable Long id){
		
		addressService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
