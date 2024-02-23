package com.redis.service;

import com.redis.entity.Employee;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
    public class EmployeeServiceImpl implements EmployeeService{

    private static final String EMPLOYEE_KEY = "Employee";

    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Long, Employee> hashOperations;

    public EmployeeServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init(){
        hashOperations=redisTemplate.opsForHash();
    }

    @Override
    public String createEmployee(Employee employee) {
        hashOperations.put(EMPLOYEE_KEY,employee.getId(),employee);
        return "Employee Created Successfully";
    }

    @Override
    public String updateEmployee(Employee employee) {
        hashOperations.put(EMPLOYEE_KEY,employee.getId(),employee);
        return "Employee Updated Successfully";
    }

    @Override
    public void deleteEmployee(Long id) {
        hashOperations.delete(EMPLOYEE_KEY, id);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return hashOperations.get(EMPLOYEE_KEY, id);
    }

    @Override
    public List<Employee> getAllEmployees(String key) {
        return hashOperations.values(key);
    }

    @Override
    public List<Employee> findEmployeeById(List<Long> id) {
        return hashOperations.multiGet(EMPLOYEE_KEY, id);
    }

    @Override
    public Map<Long, Employee> getAll() {
        return hashOperations.entries(EMPLOYEE_KEY);
    }
}
