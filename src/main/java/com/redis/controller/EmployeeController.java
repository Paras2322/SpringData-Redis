package com.redis.controller;

import com.redis.entity.Employee;
import com.redis.entity.EmployeeIdReq;
import com.redis.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value="create")
    public ResponseEntity<Object>createEmployee(@RequestBody Employee employee){
        try {
            employeeService.createEmployee(employee);
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Unable to Create Employee");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="update")
    public ResponseEntity<Object>updateEmployee(@RequestBody Employee employee) {
        try {
            Employee empl = employeeService.getEmployeeById(employee.getId());
            if (null != empl) {
                employeeService.updateEmployee(employee);
                return new ResponseEntity<>(employee, HttpStatus.OK);
            }
            return new ResponseEntity<>(employee, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unable to Update Employee");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @DeleteMapping(value="delete/{id}")
        public ResponseEntity<Object>deleteEmployee(@PathVariable("id") Long id){
            try {
                employeeService.deleteEmployee(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }catch (Exception e){
                log.error("Unable to delete Employee");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping(value="/empl/{id}")
    public ResponseEntity<Object>getById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>( employeeService.getEmployeeById(id), HttpStatus.OK);
        }catch (Exception e){
            log.error("Unable to find employee");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/empl/list")
    public ResponseEntity<Object> listAllEmployees(){
        try {
            return new ResponseEntity<>(employeeService.getAllEmployees("Employee"), HttpStatus.OK);
        }catch (Exception e){
            log.error("Unable to find all employee");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/empl/all")
    public ResponseEntity<Object> listAll(){
        try {
            Map<Long, Employee> employeeMap = employeeService.getAll();
            return new ResponseEntity<>(employeeMap, HttpStatus.OK);
        }catch (Exception e){
            log.error("Unable to find all employee");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/empl/find/id")
    public ResponseEntity<Object> findAllEmpById(@RequestBody EmployeeIdReq req) {
        try {
            List<Employee> employees = employeeService.findEmployeeById(req.getId());
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unable to find all employees by IDs");
            return new ResponseEntity<>("Unable to find employees by IDs", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
