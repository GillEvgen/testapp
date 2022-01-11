package com.gillevgenii.testapp.service.impl;

import com.gilevgenii.testapp.service.api.EmployeeService;
import com.gillevgenii.testapp.dao.api.EmployeeDao;
import com.gillevgenii.testapp.model.Employee;
import com.gillevgenii.testapp.service.impl.integration.ServiceErrorMessages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.gillevgenii.testapp.service.impl.integration.Validation.validateId;

public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final EmployeeDao employeeDao;

    public static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.parse("0000-01-01");

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAllEmployees() {
        LOGGER.debug("getAllEmployees()");

        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        LOGGER.debug("getEmployeeById(): id = " + id);

        Assert.notNull(id);
        validateId(id);

        if (id > employeeDao.getCountOfAllEmployees()) {
            throw new IllegalArgumentException(ServiceErrorMessages.ID_IS_NOT_IN_ACCEPTABLE_RANGE);
        }

        return employeeDao.getEmployeeById(id);
    }

    @Override
    public List<Employee> addEmployee(Employee employee) {
        LOGGER.debug("addEmployee(): " + employee);

        Assert.notNull(employee);
        Assert.isNull(employee.getId());
        Assert.hasText(employee.getFirst_name());
        Assert.hasText(employee.getLast_name());
        Assert.hasText(employee.getGender());
        Assert.hasText(employee.getJob_title());
        Assert.notNull(employee.getDate_of_birth());
        employeeDao.addEmployee(employee);
        return null;
    }

    @Override
    public int updateEmployee(Employee employee) {

        LOGGER.debug("updateEmployee(): " + employee);

        Assert.notNull(employee);
        Assert.notNull(employee.getId());
        Assert.hasText(employee.getFirst_name());
        Assert.hasText(employee.getLast_name());
        Assert.notNull(employee.getDate_of_birth());
        Assert.hasText(employee.getJob_title());
        Assert.hasText(employee.getGender());

        return employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        LOGGER.debug("deleteEmployeeById(): " + id);

        Assert.notNull(id);
        validateId(id);

        employeeDao.deleteEmployeeById(id);

    }
}
