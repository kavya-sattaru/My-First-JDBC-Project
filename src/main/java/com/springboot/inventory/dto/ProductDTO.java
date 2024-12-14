package com.springboot.inventory.dto;



import com.springboot.inventory.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private Integer id;
	private String name;
	private String category;
	private String brand;
	private String description; 
	private Double price;
	private Integer quantity;
	private User user;
	
	public static enum Category{
		Electronics,
		KitchenAppliances,
		Stationary,
		BeautyProducts,
		FashionWear
	}
	
}
