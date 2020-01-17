package jwd.wafepaMe.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepaMe.model.User;
import jwd.wafepaMe.web.dto.UserDTO;

@Component
public class UserToUserDTO implements Converter<User, UserDTO>{

	@Override
	public UserDTO convert(User user) {
		
		UserDTO dto = new UserDTO();
		dto.setEmail(user.getEmail());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setId(user.getId());
		
		return dto;
	}
	
	public List<UserDTO> convert(List<User> users){
		List<UserDTO> ret = new ArrayList<>();
		
		for(User user : users){
			ret.add(convert(user));
		}
		
		return ret;
	}

}
