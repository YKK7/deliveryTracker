package io.zipcoder.ykk7.repository;

import io.zipcoder.ykk7.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long>{
}
