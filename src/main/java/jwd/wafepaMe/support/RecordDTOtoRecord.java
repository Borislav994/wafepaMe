package jwd.wafepaMe.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepaMe.model.Activity;
import jwd.wafepaMe.model.Record;
import jwd.wafepaMe.model.User;
import jwd.wafepaMe.service.ActivityService;
import jwd.wafepaMe.service.RecordService;
import jwd.wafepaMe.service.UserService;
import jwd.wafepaMe.web.dto.RecordDTO;

@Component
public class RecordDTOtoRecord implements Converter<RecordDTO, Record> {

	@Autowired
	private RecordService recordService;

	@Autowired
	private UserService userService;

	@Autowired
	private ActivityService activityService;

	@Override
	public Record convert(RecordDTO recordDTO) {
		User user = userService.findOne(recordDTO.getUserId());
		Activity activity = activityService.findOne(recordDTO.getActivityId());

		if (user != null && activity != null) {

			Record record = null;

			if (recordDTO.getId() != null) {
				record = recordService.findOne(recordDTO.getId());
			} else {
				record = new Record();
			}

			record.setId(recordDTO.getId());
			record.setTime(recordDTO.getTime());
			record.setDuration(recordDTO.getDuration());
			record.setIntensity(recordDTO.getIntensity());

			record.setUser(user);
			record.setActivity(activity);

			return record;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Record> convert(List<RecordDTO> recordDTOs) {
		List<Record> ret = new ArrayList<>();

		for (RecordDTO recordDTO : recordDTOs) {
			ret.add(convert(recordDTO));
		}

		return ret;
	}

}
