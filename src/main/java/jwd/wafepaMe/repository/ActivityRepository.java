package jwd.wafepaMe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepaMe.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	List<Activity> findByName(String name);

//	@Query("SELECT a FROM Activity a WHERE a.name = :name")
//	List<Activity> findByName(@Param("name") String name);
}
