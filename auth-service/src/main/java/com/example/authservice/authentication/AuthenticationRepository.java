package com.example.authservice.authentication;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AuthenticationRepository extends CrudRepository<Authentication, Integer> {

    @Query("SELECT a FROM Authentication a where a.username = :username")
    Authentication findByUsername(@Param("username") String username);
}
