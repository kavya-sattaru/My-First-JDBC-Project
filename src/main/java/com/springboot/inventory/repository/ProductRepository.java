package com.springboot.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.inventory.model.Product;


public interface ProductRepository extends JpaRepository<Product,Integer>{
	List<Product> findByUserId(Integer userId);
}

