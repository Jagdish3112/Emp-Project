package com.Emp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Emp.entity.Employee;
import com.Emp.repository.EmpRepo;

@Service
public class EmpService {

	@Autowired
	private EmpRepo repo;
	
	public void addEmp(Employee emp)
	{
		repo.save(emp);
	}
	
	public List<Employee> getAllEmp()
	{
		return repo.findAll();
	}
	
	public Employee getEmpById(int id)
	{
		Optional<Employee> e=repo.findById(id);
		if(e.isPresent())
	    {
		    return e.get();	
		}
		return null;
	}
	
	public void deleteEmp(int id)
	{
		repo.deleteById(id);
	}
}
