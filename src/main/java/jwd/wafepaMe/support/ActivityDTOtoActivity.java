package jwd.wafepaMe.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepaMe.model.Activity;
import jwd.wafepaMe.service.ActivityService;
import jwd.wafepaMe.web.dto.ActivityDTO;

@Component
public class ActivityDTOtoActivity implements Converter<ActivityDTO, Activity> {

	@Autowired
	private ActivityService activityService;

	@Override
	public Activity convert(ActivityDTO source) {
		Activity activity;
		if (source.getId() != null) {
			activity = activityService.findOne(source.getId());
		} else {
			activity = new Activity();
		}
		activity.setId(source.getId());
		activity.setName(source.getName());

		return activity;
	}

	public List<Activity> convert(List<ActivityDTO> source) {
		List<Activity> retVal = new ArrayList<>();

		for (ActivityDTO dto : source) {
			retVal.add(convert(dto));
		}

		return retVal;
	}

}
