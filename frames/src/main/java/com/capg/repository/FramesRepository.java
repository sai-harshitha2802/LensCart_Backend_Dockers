package com.capg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.Frames;
@Repository
public interface FramesRepository extends JpaRepository<Frames, String> {

   

}
