package com.springboot.jpademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpademo.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile,Integer>{

}
