package jwd.wafepaMe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepaMe.model.User;
import jwd.wafepaMe.repository.UserRepository;
import jwd.wafepaMe.service.UserService;

@Service
public class JpaUserService implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public List<User> saveList(List<User> users) {
		// TODO Auto-generated method stub
		return userRepository.save(users);
	}

	@Override
	public Page<User> findAll(int page) {
		return userRepository.findAll(new PageRequest(page, 10));
	}
}
