package jwd.wafepaMe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepaMe.model.Address;
import jwd.wafepaMe.model.User;

@Repository
public interface AddressRepositiry extends JpaRepository<Address, Long> {

	List<Address> findByUserId(Long userId);

	List<Address> findByUser(User user);
}
