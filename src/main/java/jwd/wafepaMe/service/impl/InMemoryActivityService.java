package jwd.wafepaMe.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import jwd.wafepaMe.model.Activity;
import jwd.wafepaMe.service.ActivityService;

//@Service
public class InMemoryActivityService implements ActivityService {

	private Map<Long, Activity> activities = new HashMap<>();
	private Long nextId = 1l;

	@Override
	public List<Activity> findAll() {

		return new ArrayList<>(activities.values());
	}

	@Override
	public Activity findOne(Long id) {

		return activities.get(id);
	}

	@Override
	public Activity save(Activity activity) {

		if (activity.getId() == null) {
			activity.setId(nextId);
			nextId++;
		}
		activities.put(activity.getId(), activity);
		return activity;
	}

	@Override
	public Activity delete(Long id) {

		if (!activities.containsKey(id)) {
			throw new IllegalArgumentException("Tried to remove non-existing activity!");
		}

		return activities.remove(id);
	}

	@Override
	public List<Activity> findByName(String name) {

		ArrayList<Activity> activitiesByName = new ArrayList<Activity>();
		for (Activity ac : activities.values()) {
			if (ac.getName().equals(name)) {
				activitiesByName.add(ac);
			}
		}
		return activitiesByName;
	}

	@Override
	public List<Activity> saveList(List<Activity> activitiesSave) {

		List<Activity> ret = new ArrayList<>();

		for (Activity ac : activitiesSave) {

			Activity saved = save(ac);

			if (saved != null) {
				ret.add(saved);
			}
		}
		return ret;
	}

	@Override
	public void removeList(List<Long> ids) {

		for (Long idIt : ids) {
			delete(idIt);
		}
	}
}
