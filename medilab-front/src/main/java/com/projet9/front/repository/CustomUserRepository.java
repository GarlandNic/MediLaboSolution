package com.projet9.front.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projet9.front.model.CustomUser;

@Repository
public interface CustomUserRepository extends CrudRepository<CustomUser, Integer> {

	Optional<CustomUser> findByUsername(String username);

}
