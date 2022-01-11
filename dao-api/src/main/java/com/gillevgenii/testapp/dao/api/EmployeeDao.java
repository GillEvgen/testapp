package com.gillevgenii.testapp.dao.api;

import com.gillevgenii.testapp.model.Employee;

import javax.naming.OperationNotSupportedException;
import java.util.List;

/**
 * EmployeeDao interface
 */

public interface EmployeeDao {

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
     * Get list of employees with some name
     *
     * @param firstName employees name
     * @return list of employees with this name
     */

    /**
     * Get count of all employees
     *
     * @return count of all employees
     */
    int getCountOfAllEmployees();


    List<Employee> getEmployeeByFirstName(String firstName) throws OperationNotSupportedException;


    /**
     * Add employee
     *
     * @param employee employee object
     */

    Employee addEmployee(Employee employee);

    /**
     * Update employee
     *
     * @param employee employee object
     */
    int updateEmployee(Employee employee);

    /**
     * Delete employee
     *
     * @param id employee identifier
     */
    void deleteEmployeeById(Integer id);



}
