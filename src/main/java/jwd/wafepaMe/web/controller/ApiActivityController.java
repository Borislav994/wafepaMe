package jwd.wafepaMe.web.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepaMe.model.Activity;
import jwd.wafepaMe.service.ActivityService;
import jwd.wafepaMe.support.ActivityDTOtoActivity;
import jwd.wafepaMe.support.ActivityToActivityDTO;
import jwd.wafepaMe.web.dto.ActivityDTO;

@RestController
@RequestMapping("/api/activities")
public class ApiActivityController {

	@Autowired
	private ActivityService activitiService;

	@Autowired
	private ActivityToActivityDTO toDTO;

	@Autowired
	private ActivityDTOtoActivity toActivity;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ActivityDTO>> getActivities(
			@RequestParam(value = "name", required = false) String name) {

		List<Activity> activities;

		if (name != null) {
			activities = activitiService.findByName(name);
		} else {
			activities = activitiService.findAll();
		}

		return new ResponseEntity<>(toDTO.convert(activities), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<ActivityDTO> getOne(@PathVariable Long id) {
		Activity ac = activitiService.findOne(id);
		if (ac == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(ac), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<ActivityDTO> delete(@PathVariable Long id) {
		Activity ac = activitiService.delete(id);
		if (ac.getId() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(ac), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ActivityDTO> add(@RequestBody ActivityDTO acToAdd) {

		Activity saved = activitiService.save(
				toActivity.convert(acToAdd));

		return new ResponseEntity<>(toDTO.convert(saved), HttpStatus.CREATED);
	}

	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<ActivityDTO> edit(@PathVariable Long id, @RequestBody ActivityDTO acEdit) {

		if (!id.equals(acEdit.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Activity persisted = activitiService.save(
				toActivity.convert(acEdit));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
