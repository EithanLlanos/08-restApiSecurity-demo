package com.springCourse.restApiSecurityDemo.dao;

import com.springCourse.restApiSecurityDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
