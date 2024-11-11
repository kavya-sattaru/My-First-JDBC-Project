package studentdataapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;
import studentdataapp.model.Student;

@Component
public class StudentDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void createStudent(Student student) {
		if(student.getId()==0) {
			entityManager.persist(student);
		}
		else {
			entityManager.merge(student);
		}
	}
	
	//Getting all Students 
	public List<Student> getStudents(){
		return entityManager.createQuery("SELECT p FROM Student p",Student.class)
				            .getResultList();
	}
	
	//Delete single Student
	@Transactional
	public void deleteStudent(int sid) {
		Student student=entityManager.find(Student.class, sid);
		if(student!=null) {
			entityManager.remove(student);
		}
	}
	
	//Get single Student
	public Student getStudent(int sid) {
		return entityManager.find(Student.class,sid);
	}
	
}
