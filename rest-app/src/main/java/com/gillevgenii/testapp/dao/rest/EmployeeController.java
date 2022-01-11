package com.gillevgenii.testapp.dao.rest;

import com.gilevgenii.testapp.service.api.EmployeeService;
import com.gillevgenii.testapp.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employees")
    public List<Employee> getAllEmployees() {
        LOGGER.debug("getAllEmployees()");
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value = "/employee/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        LOGGER.debug("getEmployeeById(): id = " + id);
        return employeeService.getEmployeeById(id);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.PUT)
    public Employee updateEmployee(@RequestBody Employee employee) {
        LOGGER.debug("updateEmployee(): " + employee);
        employeeService.updateEmployee(employee);
        return employee;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public Employee addEmployee(@RequestBody Employee employee) {
        LOGGER.debug("addEmployee(): " + employee);
        employeeService.addEmployee(employee);
        return employee;
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public String deleteEmployeeById(@PathVariable Integer id) {
        LOGGER.debug("deleteEmployeeById(): id = " + id);
        employeeService.deleteEmployeeById(id);
        return "Employee with id: " + id + " was successfully deleted!!!";
    }
}
