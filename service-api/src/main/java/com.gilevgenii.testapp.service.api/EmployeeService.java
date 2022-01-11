package com.gilevgenii.testapp.service.api;

import com.gillevgenii.testapp.model.Employee;

import java.util.List;

/**
 * Employee service interface
 */

public interface EmployeeService {

    /**
     * Get all employees list
     *
     * @return list with all employees
     */
    List<Employee> getAllEmployees();

    /**
     * Get employee with some id
     *
     * @param id employee identifier
     * @return employee object
     */
    Employee getEmployeeById(Integer id);


    /**
     * Add employee
     *
     * @param employee employee object
     */
    List<Employee> addEmployee(Employee employee);

    /**
     * Update employee
     *
     * @param employee employee object
     * @return count of changed record(employees)
     */
    int updateEmployee(Employee employee);


    /**
     * Delete employee
     *
     * @param id employee identifier
     */
    void deleteEmployeeById(Integer id);

}
