package com.springboot.inventory.service;

import java.util.List;
import java.util.Optional;

import javax.naming.directory.InvalidAttributeIdentifierException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.inventory.dto.ProductDTO;
import com.springboot.inventory.dto.ResponseDTO;
import com.springboot.inventory.model.Product;
import com.springboot.inventory.model.User;
import com.springboot.inventory.repository.ProductRepository;
import com.springboot.inventory.repository.UserRepository;

@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;

	public ProductService(UserRepository userRepository, ProductRepository productRepository) {
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}

	public ResponseDTO createProduct(Integer userId, ProductDTO productDTO) {
		 logger.info("Creating product for userId: {}", userId);
		try {

			if (productDTO.getName() != null && !productDTO.getName().isEmpty()
					&& !productDTO.getName().matches("^[a-zA-Z]+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Name, allow only alphabets");
			}
			if (productDTO.getCategory() != null && !productDTO.getCategory().isEmpty()
					&& !productDTO.getCategory().matches("^[a-zA-Z]+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Category, allow only alphabets");
			}
			if (productDTO.getBrand() != null && !productDTO.getBrand().isEmpty()
					&& !productDTO.getBrand().matches("^[a-zA-Z]+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Brand, allow only alphabets");
			}
			if (productDTO.getDescription() != null && !productDTO.getDescription().isEmpty()
					&& !productDTO.getDescription().matches("^[a-zA-Z]+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Description, allow only alphabets");
			}
			if (productDTO.getPrice() != null && (productDTO.getPrice() == 0)
					&& !productDTO.getPrice().toString().matches("^\\d+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Price, allow only numbers");
			}
			if (productDTO.getQuantity() != null && (productDTO.getQuantity() == 0)
					&& !productDTO.getQuantity().toString().matches("^\\d+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Quantity, allow only numbers");
			}

			User user = userRepository.findById(userId)
					.orElseThrow(() -> new RuntimeException("User not found with Id : " + userId));
			logger.debug("User found: {}", user);
			Product product = new Product();

			product.setName(productDTO.getName());
			product.setCategory(productDTO.getCategory());
			product.setBrand(productDTO.getBrand());
			product.setDescription(productDTO.getDescription());
			product.setPrice(productDTO.getPrice());
			product.setQuantity(productDTO.getQuantity());
			product.setUser(user);

			Product productFromDB = productRepository.save(product);
			logger.info("Product created successfully: {}", productFromDB);

			ProductDTO response = new ProductDTO();
			response.setId(productFromDB.getId());
			response.setName(productFromDB.getName());
			response.setCategory(productFromDB.getCategory());
			response.setBrand(productFromDB.getBrand());
			response.setDescription(productFromDB.getDescription());
			response.setPrice(productDTO.getPrice());
			response.setQuantity(productDTO.getQuantity());
			response.setUser(productFromDB.getUser());

			return new ResponseDTO(ResponseDTO.Status.SUCCESS, response);
		} catch (InvalidAttributeIdentifierException e) {
			logger.error("Validation error: {}", e.getMessage());
			return new ResponseDTO(ResponseDTO.Status.FAIL, 422, e.getMessage(), null);
		} catch (Exception e) {
			logger.error("Error occurred while creating product: ", e);
		}
		return null;
	}

	public List<Product> getProducts() {
        logger.info("Fetching all products");
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while fetching products: ", e);
        }
        return null;
    }

	public ResponseDTO updateProduct(Integer productId, ProductDTO productDTO) {
		logger.info("Updating product with ID: {}", productId);
		try {

			if (productDTO.getName() != null && !productDTO.getName().isEmpty()
					&& !productDTO.getName().matches("^[a-zA-Z]+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Name, allow only alphabets");
			}
			if (productDTO.getCategory() != null && !productDTO.getCategory().isEmpty()
					&& !productDTO.getCategory().matches("^[a-zA-Z]+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Category, allow only alphabets");
			}
			if (productDTO.getBrand() != null && !productDTO.getBrand().isEmpty()
					&& !productDTO.getBrand().matches("^[a-zA-Z]+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Brand, allow only alphabets");
			}
			if (productDTO.getDescription() != null && !productDTO.getDescription().isEmpty()
					&& !productDTO.getDescription().matches("^[a-zA-Z]+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Description, allow only alphabets");
			}
			if (productDTO.getPrice() != null && (productDTO.getPrice() == 0)
					&& !productDTO.getPrice().toString().matches("^\\d+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Price, allow only numbers");
			}
			if (productDTO.getQuantity() != null && (productDTO.getQuantity() == 0)
					&& !productDTO.getQuantity().toString().matches("^\\d+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Quantity, allow only numbers");
			}

			Optional<Product> productOptionalFromDB = productRepository.findById(productId);

			if (productOptionalFromDB.isEmpty()) {
				logger.warn("Product not found with ID: {}", productId);
				return null;
			}

			Product productFromDB = productOptionalFromDB.get();
			logger.debug("Existing product data: {}", productFromDB);

			if (productDTO.getName() != null && !productDTO.getName().isEmpty()) {
				productFromDB.setName(productDTO.getName());
			}
			if (productDTO.getCategory() != null && !productDTO.getCategory().isEmpty()) {
				productFromDB.setCategory(productDTO.getCategory());
			}
			if (productDTO.getBrand() != null && !productDTO.getBrand().isEmpty()) {
				productFromDB.setBrand(productDTO.getBrand());
			}
			if (productDTO.getDescription() != null && !productDTO.getDescription().isEmpty()) {
				productFromDB.setDescription(productDTO.getDescription());
			}
			if (productDTO.getPrice() != null && !(productDTO.getPrice() == 0)) {
				productFromDB.setPrice(productDTO.getPrice());
			}
			if (productDTO.getQuantity() != null && !(productDTO.getQuantity() == 0)) {
				productFromDB.setQuantity(productDTO.getQuantity());
			}
			if (productDTO.getUser() != null && !productDTO.getUser().toString().isEmpty()) {
				productFromDB.setUser(productDTO.getUser());
			}

			productRepository.save(productFromDB);
			logger.info("Product updated successfully: {}", productFromDB);

			ProductDTO response = new ProductDTO();
			response.setId(productFromDB.getId());
			response.setName(productFromDB.getName());
			response.setCategory(productFromDB.getCategory());
			response.setBrand(productFromDB.getBrand());
			response.setDescription(productFromDB.getDescription());
			response.setPrice(productDTO.getPrice());
			response.setQuantity(productDTO.getQuantity());
			response.setUser(productFromDB.getUser());

			return new ResponseDTO(ResponseDTO.Status.SUCCESS, response);
		} catch (InvalidAttributeIdentifierException e) {
			 logger.error("Validation error: {}", e.getMessage());
			return new ResponseDTO(ResponseDTO.Status.FAIL, 422, e.getMessage(), null);
		} catch (Exception e) {
			 logger.error("Error occurred while updating product: ", e);
		}
		return null;
	}

	@Transactional
	 public ResponseDTO deleteProduct(Integer productId) {
	        logger.info("Deleting product with ID: {}", productId);
	        try {
	            Optional<Product> productOptionalFromDB = productRepository.findById(productId);
	            if (productOptionalFromDB.isEmpty()) {
	                logger.warn("Product not found with ID: {}", productId);
	                return null;
	            }

	            productRepository.deleteById(productId);
	            
	            logger.info("Product deleted successfully with ID: {}", productId);

	            return new ResponseDTO(ResponseDTO.Status.SUCCESS, null);
	        } catch (Exception e) {
	            logger.error("Error occurred while deleting product: ", e);
	        }
	        return null;
	    }

	    public Optional<Product> getProduct(Integer productId) {
	        logger.info("Start : Fetching product with ID: {}", productId);
	        try {
	            Optional<Product> product = productRepository.findById(productId);
	            logger.info("End: Fetched user with ID: {}", productId);
	            return product;
	        } catch (Exception e) {
	            logger.error("Error occurred while fetching product: ", e);
	        }
	        return null;
	    }

	    public List<Product> getUserProducts(Integer userId) {
	        logger.info("Fetching products for userId: {}", userId);
	        try {
	            return productRepository.findByUserId(userId);
	        } catch (Exception e) {
	            logger.error("Error occurred while fetching user products: ", e);
	        }
	        return null;
	    }

}
