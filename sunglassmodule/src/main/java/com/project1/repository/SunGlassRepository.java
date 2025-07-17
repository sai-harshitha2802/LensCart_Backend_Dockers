package com.project1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1.entity.SunGlass;

public interface SunGlassRepository extends JpaRepository<SunGlass, String>{
	
	Optional<SunGlass> findById(String sunGlassId);

}
