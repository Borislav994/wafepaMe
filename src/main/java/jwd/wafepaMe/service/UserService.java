package jwd.wafepaMe.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jwd.wafepaMe.model.User;

public interface UserService {

	List<User> findAll();

	User findOne(Long id);

	User save(User user);

	void delete(Long id);

	List<User> saveList(List<User> users);
	
	Page<User> findAll(int page);
}
