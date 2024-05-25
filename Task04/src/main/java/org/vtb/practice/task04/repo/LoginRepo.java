package org.vtb.practice.task04.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vtb.practice.task04.model.Login;

import java.util.List;

public interface LoginRepo extends CrudRepository<Login, Integer> {
    List<Login> findByApplication(String application);
}
