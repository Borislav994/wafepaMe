package jwd.wafepaMe.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepaMe.model.Record;
import jwd.wafepaMe.service.RecordService;
import jwd.wafepaMe.support.RecordDTOtoRecord;
import jwd.wafepaMe.support.RecordToRecordDTO;
import jwd.wafepaMe.web.dto.RecordDTO;

@RestController
@RequestMapping(value = "/api/records")
public class ApiRecordController {

	@Autowired
	private RecordService recordService;
	
	@Autowired
	private RecordToRecordDTO toDTO;
	
	@Autowired
	private RecordDTOtoRecord toRecord;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<RecordDTO>> getRecords(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {

		Page<Record> recordsPage = recordService.findAll(pageNum);

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(recordsPage.getTotalPages()));

		return new ResponseEntity<>(toDTO.convert(recordsPage.getContent()),
				headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<RecordDTO> getRecord(@PathVariable Long id) {

		Record record = recordService.findOne(id);
		if (record == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(record), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<RecordDTO> delete(@PathVariable Long id) {

		Record deleted = recordService.delete(id);

		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	ResponseEntity<RecordDTO> add(@Validated @RequestBody RecordDTO newRecordDTO) {

		Record savedRecord = recordService.save(
				toRecord.convert(newRecordDTO));

		return new ResponseEntity<>(toDTO.convert(savedRecord), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	ResponseEntity<RecordDTO> edit(@RequestBody RecordDTO recordNew, @PathVariable Long id) {

		if (!id.equals(recordNew.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Record persisted = recordService.save(
				toRecord.convert(recordNew));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
