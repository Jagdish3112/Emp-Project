package com.Emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Emp.entity.Employee;
import com.Emp.service.EmpService;

import jakarta.servlet.http.HttpSession;


@Controller
public class EmpController {

	@Autowired
	private EmpService service;
	
	@GetMapping("/index")
	public String home(HttpSession session, Model m) {
	    List<Employee> emp = service.getAllEmp();
	    m.addAttribute("emp", emp);

	    String msg = (String) session.getAttribute("msg");

	    if (msg != null) {
	        m.addAttribute("msg", msg);
	        session.removeAttribute("msg"); 
	    }

	    return "index";
	}
	
	@GetMapping("/addEmp")
	public String addEmpFrom()
	{
		return "addEmp";
	}
	
	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee emp, HttpSession session) {
	    System.out.println("Message set in session: " + session.getAttribute("msg")); 
	    service.addEmp(emp);
	    session.setAttribute("msg", "Employee Added Successfully..");
	    return "redirect:/"; 
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable String id, Model m) {
	    int numericId = 0;
	    numericId = Integer.parseInt(id);  

	    Employee e = service.getEmpById(numericId);
	    if (e == null) {
	        return "employeeNotFound";  
	    }
	    m.addAttribute("emp", e);
	    return "edit";  
	}

	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee emp, HttpSession session) {
	    service.addEmp(emp);

	    session.setAttribute("msg", "Emp Data Updated Successfully..");

	    return "redirect:/";  
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id,HttpSession session)
	{
		service.deleteEmp(id);
		return "redirect:/";
	}
}
