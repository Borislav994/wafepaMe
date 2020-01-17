package jwd.wafepaMe.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jwd.wafepaMe.model.Activity;
import jwd.wafepaMe.service.impl.InMemoryActivityService;

public class InMemoryActivityServiceTest {

	private ActivityService activityService;

	@Before
	public void setUp() {
		activityService = new InMemoryActivityService();

		Activity running = new Activity("Running");
		Activity swimming = new Activity("Swimming");
		Activity dancing = new Activity("Dancing");
		Activity dancing2 = new Activity("Dancing");

		activityService.save(swimming);
		activityService.save(running);
		activityService.save(dancing);
		activityService.save(dancing2);

	}

	@Test
	public void testFindOne() {
		Activity res = activityService.findOne(2l);

		Assert.assertNotNull(res);
		Assert.assertEquals("Running", res.getName());
	}

	@Test
	public void testFindAll() {
		List<Activity> res = activityService.findAll();

		Assert.assertEquals(4, res.size());
	}

	@Test
	public void testSave() {
		Activity dancing = new Activity("Dancing");
		Activity res = activityService.save(dancing);

		Assert.assertEquals("Dancing", res.getName());
	}

	@Test
	public void testDelete() {
		Activity res = activityService.delete(2l);

		Assert.assertEquals("Running", res.getName());
	}

	@Test
	public void testFindByName() {
		List<Activity> res = activityService.findByName("Dancing");

		Assert.assertEquals(2, res.size());

	}

	@Test
	public void testSaveList() {
		List<Activity> ac = new ArrayList<>();
		ac.add(new Activity("Learning"));
		ac.add(new Activity("Boxing"));
		activityService.saveList(ac);

		Assert.assertEquals(6, activityService.findAll().size());
	}

	@Test
	public void testRemoveList() {
		List<Activity> acFor = activityService.findAll();
		List<Long> ac = new ArrayList<>();
		for (Activity acIt : acFor) {
			if (acIt.getId() == 5 || acIt.getId() == 6) {
				ac.add(acIt.getId());
			}
		}
		activityService.removeList(ac);

		Assert.assertEquals(4, activityService.findAll().size());
	}
}
