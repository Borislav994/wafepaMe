package jwd.wafepaMe.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepaMe.model.User;
import jwd.wafepaMe.repository.UserRepository;
import jwd.wafepaMe.web.dto.UserDTO;

@Component
public class UserDTOtoUser implements Converter<UserDTO, User> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User convert(UserDTO dto) {
		User user = null;
		if (dto.getId() == null) {
			user = new User();
		} else {
			user = userRepository.findOne(dto.getId());
			if (user == null) {
				throw new IllegalStateException("Editing non-existant User");
			}
		}

		user.setEmail(dto.getEmail());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());

		return user;
	}

	public List<User> convert(List<UserDTO> usersDTO) {
		List<User> ret = new ArrayList<>();

		for (UserDTO dto : usersDTO) {
			ret.add(convert(dto));
		}

		return ret;
	}

}
