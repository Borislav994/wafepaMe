package jwd.wafepaMe.service;

import java.util.List;

import jwd.wafepaMe.model.Activity;

public interface ActivityService {

	List<Activity> findAll();

	Activity findOne(Long id);

	Activity save(Activity activity);

	Activity delete(Long id);

	List<Activity> findByName(String name);

	List<Activity> saveList(List<Activity> activities);

	void removeList(List<Long> activities);

}
