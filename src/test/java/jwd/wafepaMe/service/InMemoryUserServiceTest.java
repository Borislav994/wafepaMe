package jwd.wafepaMe.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jwd.wafepaMe.model.User;
import jwd.wafepaMe.service.impl.InMemoryUserService;

public class InMemoryUserServiceTest {

	private UserService userService;

	@Before
	public void setUp() {
		userService = new InMemoryUserService();

		userService.save(new User("Ana"));
		userService.save(new User("Strahinja"));
		userService.save(new User("Zoran"));
		userService.save(new User("Milos"));

	}

	@Test
	public void testFindOne() {
		User us = userService.findOne(1l);
		Assert.assertEquals("Ana", us.getFirstName());
	}

	@Test
	public void testFindAll() {
		List<User> us = userService.findAll();
		Assert.assertEquals(4, us.size());
	}

	@Test
	public void testSave() {
		User us3 = new User("Zivkic");
		User res = userService.save(us3);

		Assert.assertEquals("Zivkic", res.getFirstName());
	}

	

	@Test
	public void testSaveList() {
		List<User> res = new ArrayList<User>();
		res.add(new User(5l, null, null, "markso", "marksic"));
		res.add(new User(6l, null, null, "Nikoaas", "Nikic"));
		userService.saveList(res);

		Assert.assertEquals(6, userService.findAll().size());
	}

	@Test
	public void testDeleteList() {
		List<User> us = userService.findAll();
		List<Long> ids = new ArrayList<Long>();
		for (User usIt : us) {
			if (usIt.getId() == 5 || usIt.getId() == 6) {
				ids.add(usIt.getId());
			}
		}
		Assert.assertEquals(4, userService.findAll().size());
	}
}
