package jwd.wafepaMe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepaMe.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findByFirstNameContainingAndLastNameContaining(
			String firstname, String lastname);
}
