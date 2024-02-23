package com.redis.service;

import com.redis.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    String createEmployee(Employee employee);
    String updateEmployee(Employee employee);
    void deleteEmployee(Long id);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees(String key);
    List<Employee> findEmployeeById(List<Long> id);
    Map<Long,Employee> getAll();

}
