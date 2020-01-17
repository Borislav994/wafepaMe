package jwd.wafepaMe.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import jwd.wafepaMe.model.User;
import jwd.wafepaMe.service.UserService;

//@Service
public class InMemoryUserService implements UserService {

	private Map<Long, User> users = new HashMap<>();
	private Long nextId = 1l;

	@Override
	public List<User> findAll() {
		return new ArrayList<>(users.values());
	}

	@Override
	public User findOne(Long id) {
		return users.get(id);
	}

	@Override
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(nextId);
			nextId += 1;
		}
		users.put(user.getId(), user);
		return user;
	}

	@Override
	public void delete(Long id) {
		if (!users.containsKey(id)) {
			throw new IllegalArgumentException("Tried to remove non-existing activity!");
		}
		 users.remove(id);
	}

	@Override
	public List<User> saveList(List<User> users) {
		List<User> ret = new ArrayList<User>();
		for (User us : users) {
			User saved = save(us);

			if (saved != null) {
				ret.add(saved);
			}
		}
		return ret;
	}

	@Override
	public Page<User> findAll(int page) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
