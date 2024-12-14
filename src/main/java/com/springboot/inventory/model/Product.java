package com.springboot.inventory.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String category;
	private String brand;
	private String description; 
	private Double price;
	private Integer quantity;
	
	private LocalDateTime addedOn;
    private LocalDateTime updatedOn;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	@JsonBackReference
    private User user;
	
	public static enum Category{
		Electronics,
		KitchenAppliances,
		Stationary,
		BeautyProducts,
		FashionWear
		
	}
	@PrePersist
    public void onCreate() {
        addedOn = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedOn = LocalDateTime.now();
    }
}
