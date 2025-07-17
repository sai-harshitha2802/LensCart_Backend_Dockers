package com.project1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1.entity.Lenses;
 
 
 
@Repository
public interface LensesRepository extends JpaRepository<Lenses, String> {
	
	Optional<Lenses> findById(String lensId);

 
}
