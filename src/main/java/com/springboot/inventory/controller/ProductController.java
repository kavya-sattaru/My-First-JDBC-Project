package com.springboot.inventory.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.inventory.dto.ProductDTO;
import com.springboot.inventory.dto.ResponseDTO;
import com.springboot.inventory.model.Product;
import com.springboot.inventory.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/{userId}")
	public ResponseDTO addProducts(@PathVariable("userId") Integer userId,@RequestBody ProductDTO productDTO) {
		return productService.createProduct(userId,productDTO);
	}
	
	@GetMapping
	public List<Product> getProducts() {
		return productService.getProducts();
	}
	
	@PutMapping("/{productId}")
	public ResponseDTO updateProduct(@PathVariable("productId") Integer productId,@RequestBody ProductDTO productDTO) {
		return productService.updateProduct(productId,productDTO);
		
	}
	@DeleteMapping("/{productId}")
	public ResponseDTO deleteProduct(@PathVariable("productId") Integer productId) {
		return productService.deleteProduct(productId);
	}
	@GetMapping("/{productId}")
	public Optional<Product> getProduct(@PathVariable("productId") Integer productId) {
		return productService.getProduct(productId);
	}
	
	@GetMapping("user/{userId}")
	public List<Product> getUserProducts(@PathVariable("userId") Integer userId) {
		return productService.getUserProducts(userId);
	}


}
