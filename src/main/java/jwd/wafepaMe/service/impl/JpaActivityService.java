package jwd.wafepaMe.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepaMe.model.Activity;
import jwd.wafepaMe.repository.ActivityRepository;
import jwd.wafepaMe.service.ActivityService;


@Service
@Transactional
public class JpaActivityService implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public List<Activity> findAll() {
		// TODO Auto-generated method stub
		return activityRepository.findAll();
	}

	@Override
	public Activity findOne(Long id) {
		// TODO Auto-generated method stub
		return activityRepository.findOne(id);
	}

	@Override
	public Activity save(Activity activity) {
		// TODO Auto-generated method stub
		return activityRepository.save(activity);
	}

	@Override
	public Activity delete(Long id) {
		Activity toDelete = activityRepository.findOne(id);
		if (toDelete != null) {
			activityRepository.delete(toDelete);
		}
		return toDelete;
	}

	@Override
	public List<Activity> findByName(String name) {
		// TODO Auto-generated method stub
		return activityRepository.findByName(name);
	}

	@Override
	public List<Activity> saveList(List<Activity> activities) {
		// TODO Auto-generated method stub
		return activityRepository.save(activities);
	}

	@Override
	public void removeList(List<Long> activities) {
		// TODO Auto-generated method stub
		for (Long ids : activities) {
			activityRepository.delete(ids);
		}

	}

}
