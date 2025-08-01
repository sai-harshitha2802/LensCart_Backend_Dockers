package com.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1.entity.Glass;

@Repository
public interface GlassRepository extends JpaRepository<Glass, String>{

}
