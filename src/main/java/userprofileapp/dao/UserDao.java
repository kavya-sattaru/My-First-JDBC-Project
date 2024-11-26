package userprofileapp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import userprofileapp.model.Users;





@Component
public class UserDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	// Create User 
	@Transactional
	public void createUser(Users user) {
		this.hibernateTemplate.saveOrUpdate(user);
		
	}
	
	
	// Getting all User
	public List<Users> getUsers() {
		List<Users> userList = this.hibernateTemplate.loadAll(Users.class);
		return userList;
	}
	
	// Delete single User
	@Transactional
	public void deleteUser(int pid) {
		Users p = this.hibernateTemplate.load(Users.class, pid);
		this.hibernateTemplate.delete(p);
	}
	
	// Get single User
	public Users getUser(int pid) {
		return this.hibernateTemplate.get(Users.class, pid);
  }
	
}