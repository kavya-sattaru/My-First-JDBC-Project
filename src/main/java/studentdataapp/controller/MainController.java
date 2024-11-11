package studentdataapp.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import studentdataapp.dao.StudentDao;
import studentdataapp.model.Student;


@Controller
public class MainController {
	
	@Autowired
	private StudentDao studentDao;
	
	@RequestMapping("/home")
	public String viewHome(Model model) {
		List<Student> student= studentDao.getStudents();
		model.addAttribute("student", student);
		return "home";
	}
	
	@RequestMapping("/addstudent")
	public String addStudent(Model model) {
		model.addAttribute("title", "Add Student");
		return "add_student";
	}
	
	@RequestMapping(value="/handle-student",method=RequestMethod.POST)
	public RedirectView handleStudent(@ModelAttribute Student student,HttpServletRequest request) {
		System.out.println(student);
		studentDao.createStudent(student);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
	
	@RequestMapping("/delete/{studentId}")
	public RedirectView deleteStudent(@PathVariable("studentId") int studentId, HttpServletRequest request) {
		this.studentDao.deleteStudent(studentId);
		RedirectView redirectView =new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
	
	@RequestMapping("/update/{studentId}")
	public String updateStudent(@PathVariable("studentId") int sid, Model model) {
		model.addAttribute("title", "Update Student");
		Student student=this.studentDao.getStudent(sid);
		model.addAttribute("Student", student);
		return "update_student";
	}
	
}
