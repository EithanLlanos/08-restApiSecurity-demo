package com.springCourse.restApiSecurityDemo.rest;

import com.springCourse.restApiSecurityDemo.entity.Employee;
import com.springCourse.restApiSecurityDemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Optional<Employee> theEmployee = employeeService.findById(employeeId);
        if (theEmployee.isEmpty()) {
            throw new RuntimeException("Employee id not found: " + employeeId);
        }
        return theEmployee.orElse(null);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Optional<Employee> tempEmployee = employeeService.findById(employeeId);

        if (tempEmployee.isEmpty()) throw new RuntimeException("Employee id not found - " + employeeId);

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }
}
