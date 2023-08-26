package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	private  EmployeeService employeeService;
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List<Employee> theEmployees=employeeService.findAll();
		// add to the  model
		theModel.addAttribute("employees", theEmployees);

		return "employee/list-employees";
	}



	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model){
		Employee employee=new Employee();
		//form will use empty employee object to bind the data
		model.addAttribute("employee",employee);
		return "/employee/employeeAdd-form";

	}

	@PostMapping("/save")
	//here the bind data of employee object will be stored to employee object

	public String save(@ModelAttribute("employee") Employee employee){

		// here we are saving that employee into db
		employeeService.save(employee);
		//use redirect for duplicate Submissions
		return "redirect:/employees/list";
	}


	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id,Model model){

		//get the employee from service
		Employee theEmployee=employeeService.findById(id);

		// set employee in the model

		model.addAttribute("employee",theEmployee);

		//prepopulate form
		return "/employee/employeeAdd-form";

	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id ){
		//del emp
		employeeService.deleteById(id);
		return "redirect:/employees/list";
	}


}









