package com.capg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.Glass;

@Repository
public interface GlassRepository extends JpaRepository<Glass, String>{

}
