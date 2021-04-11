package com.din.pridesys.controller;
 
import java.util.List;  
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Student{
	private int id;
	private String name;
	private boolean checkbox;
	private String radio;
	private String textarea;
	private String dropdown;
	@JsonBackReference
	private Address address;
	@JsonBackReference
	private List<Course> courseList;
}
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Address{
	private String empAddress;
}
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Course{
	private String subjectCourse;
}

//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "*")
@RestController  
@RequestMapping("/api/v1")
public class UserController { 
	 
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<String>> base(Model model, ModelAndView modelAndView){
		List<String> userList = List.of("admin", "manager", "user", "guest");	
		
		model.addAttribute("msg", "Hello ....");
		modelAndView.addObject("string", userList.get(0));
		
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	//RestTemplate restTemplate = new RestTemplate();
	//@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
																  consumes = MediaType.APPLICATION_JSON_VALUE) 
	@ResponseBody
	public ResponseEntity<Student> saveStu(@RequestBody Student student) {
		 // Prepare acceptable media type
//		  List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
//		  acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		 
		  // Prepare header
//		  HttpHeaders headers = new HttpHeaders();
//		  headers.setAccept(acceptableMediaTypes);
		  // Pass the new person and header
		 // HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);
		 
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> stuList() {
		Address address = new Address("Mirpur 1, Dhaka"); 
		Address address2 = new Address("Mirpur 12, Dhaka"); 
		Address address3 = new Address("Mirpur 13, Dhaka"); 
		
		List<Course> courseList = List.of(new Course("JAVA"),new Course("PYTHON"), new Course("DATA BASE"),new Course("PHP"));
		List<Course> courseList2 = List.of(new Course("JAVA2"),new Course("PYTHON2"), new Course("DATA BASE2"),new Course("PHP2"));
		List<Course> courseList3 = List.of(new Course("JAVA3"),new Course("PYTHON3"), new Course("DATA BASE3"),new Course("PHP3"));
		
		List<Student> list = List.of(new Student(1, "dinislam", true, "male", "Helloo", "java", address, courseList),
				new Student(11, "dinislam", true, "male", "Helloo", "java", address2, courseList2),
				new Student(111, "dinislam", true, "male", "Helloo", "java", address3, courseList3));
		
		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}
	


	
}