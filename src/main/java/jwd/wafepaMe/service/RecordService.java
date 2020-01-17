package jwd.wafepaMe.service;

import org.springframework.data.domain.Page;

import jwd.wafepaMe.model.Record;



public interface RecordService {
	Record findOne(Long id);
	Page<Record> findAll(int pageNum);
	Record save(Record record);
	Record delete(Long id);
	Page<Record> findByUserId(Long id, int pageNum);
}
