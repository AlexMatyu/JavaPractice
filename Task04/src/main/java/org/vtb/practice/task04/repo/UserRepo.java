package org.vtb.practice.task04.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vtb.practice.task04.model.User;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Integer> {
    List<User> findByUsername(String username);
}
