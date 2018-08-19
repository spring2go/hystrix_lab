package io.spring2go.hystrixlab.schoolservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.spring2go.hystrixlab.schoolservice.delegate.StudentServiceDelegate;
 
@RestController
public class SchoolServiceController {
     
    @Autowired
    StudentServiceDelegate studentServiceDelegate;
 
    @RequestMapping(value = "/getSchoolDetails/{schoolName}", method = RequestMethod.GET)
    public String getSchoolDetails(@PathVariable String schoolName) {
        System.out.println("Going to call student service to get data!");
        return studentServiceDelegate.callStudentService(schoolName);
    }
}