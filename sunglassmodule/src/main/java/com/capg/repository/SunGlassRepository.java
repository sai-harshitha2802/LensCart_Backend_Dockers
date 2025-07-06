package com.capg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entity.SunGlass;

public interface SunGlassRepository extends JpaRepository<SunGlass, String>{
	
	Optional<SunGlass> findById(String sunGlassId);

}
