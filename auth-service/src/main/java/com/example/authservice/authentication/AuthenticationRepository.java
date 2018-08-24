package com.example.authservice.authentication;

import org.springframework.data.repository.CrudRepository;

public interface AuthenticationRepository extends CrudRepository<Authentication, Integer> {
}
