package productcrudapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import productcrudapp.model.Product;

@Component
public class ProductDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void createProduct(Product product) {
		if(product.getId()==0) {
			entityManager.persist(product);
		}
		else {
			entityManager.merge(product);
		}
	}
	
	//Getting all products 
	public List<Product> getProducts(){
		return entityManager.createQuery("SELECT p FROM Product p",Product.class)
				            .getResultList();
	}
	
	//Delete single product
	@Transactional
	public void deleteProduct(int pid) {
		Product product=entityManager.find(Product.class, pid);
		if(product!=null) {
			entityManager.remove(product);
		}
	}
	
	//Get single Product
	public Product getProduct(int pid) {
		return entityManager.find(Product.class,pid);
	}
	
}
