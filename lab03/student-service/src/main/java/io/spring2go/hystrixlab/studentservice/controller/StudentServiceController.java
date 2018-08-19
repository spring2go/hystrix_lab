package io.spring2go.hystrixlab.studentservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.spring2go.hystrixlab.studentservice.domain.Student;

@RestController
public class StudentServiceController {
	
	private static Map<String, List<Student>> schoolDB = new HashMap<String, List<Student>>();
	
	static {
		schoolDB = new HashMap<String, List<Student>>();
		
		List<Student> students = new ArrayList<Student>();
		Student student = new Student("BoBo", "Class I");
		students.add(student);
		student = new Student("Jack", "Class II");
		students.add(student);
		
		schoolDB.put("abcschool", students);
		
		students = new ArrayList<Student>();
		student = new Student("Daniel", "Class III");
		students.add(student);
		student = new Student("William", "Class V");
		students.add(student);
		
		schoolDB.put("xyzschool", students);
	}
	
	@RequestMapping(value = "/getStudentBySchool/{schoolName}", method = RequestMethod.GET)
	public List<Student> getStudents(@PathVariable String schoolName) {
		System.out.println("Getting student for " + schoolName);
		
		randomlyRunLong();
		
		List<Student> studentList = schoolDB.get(schoolName);
		if (studentList == null) {
			studentList = new ArrayList<Student>();
			Student student = new Student("Not Found", "N/A");
			studentList.add(student);
		}
		
		return studentList;
	}
	
	private void randomlyRunLong() {
		Random rand = new Random();
		
		int randomNum = rand.nextInt(3) + 1;
		
		if (randomNum == 3) sleep();
	}
	
	private void sleep() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
