package com.gillevgenii.testapp.dao.jdbc;

import com.gillevgenii.testapp.dao.api.EmployeeDao;
import com.gillevgenii.testapp.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.naming.OperationNotSupportedException;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * EmployeeDao H2 implementation
 */
public class EmployeeDaoH2Impl implements EmployeeDao {

    private static final Logger LOGGER = LogManager.getLogger();

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${EmployeeDao.sql.getEmployeeById}")
    private String GET_EMPLOYEE_BY_ID_SQL;

    @Value("${EmployeeDao.sql.getAllEmployees}")
    private String GET_ALL_EMPLOYEES_SQL;

    @Value("${EmployeeDao.sql.addEmployee}")
    private String ADD_EMPLOYEE_SQL;

    @Value("${EmployeeDao.sql.deleteEmployeeById}")
    private String DELETE_EMPLOYEE_BY_ID_SQL;

    @Value("${EmployeeDao.sql.updateEmployee}")
    private String UPDATE_EMPLOYEE_SQL;

    @Value("${EmployeeDao.sql.getCountOfAllEmployees}")
    private String GET_COUNT_OF_ALL_EMPLOYEES_SQL;

    public static final String ID = "id";
    public static final String DATE_OF_BIRTH = "date_of_birth";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String JOB_TITLE = "job_title";
    public static final String GENDER = "gender";
    public static final String DEPARTMENT_ID = "department_id";


    public EmployeeDaoH2Impl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> getAllEmployees() {
        LOGGER.debug("getAllEmployees()");

        return jdbcTemplate.query(GET_ALL_EMPLOYEES_SQL, new EmployeeRowMapper());
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        LOGGER.debug("getEmployeeById(): id = " + id);

        SqlParameterSource source = new MapSqlParameterSource(ID, id);
        return namedParameterJdbcTemplate.queryForObject
                (GET_EMPLOYEE_BY_ID_SQL, source, new EmployeeRowMapper());
    }

    @Override
    public int getCountOfAllEmployees() {

        LOGGER.debug("getCountOfAllEmployees()");
        return jdbcTemplate.queryForObject(GET_COUNT_OF_ALL_EMPLOYEES_SQL, Integer.class);
    }

    @Override
    public List<Employee> getEmployeeByFirstName(String firstName) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        LOGGER.debug("addEmployee() :  " + employee);

        SqlParameterSource source = new BeanPropertySqlParameterSource(employee);
        namedParameterJdbcTemplate.update(ADD_EMPLOYEE_SQL, source);
        return employee;
    }

    public int updateEmployee(Employee employee) {
        LOGGER.debug("updateEmployee(): " + employee);

        SqlParameterSource source = new BeanPropertySqlParameterSource(employee);
        return namedParameterJdbcTemplate.update(UPDATE_EMPLOYEE_SQL, source);
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        LOGGER.debug("deleteEmployeeById(): id = " + id);

        SqlParameterSource source = new MapSqlParameterSource(ID, id);
        int count = namedParameterJdbcTemplate.update(DELETE_EMPLOYEE_BY_ID_SQL, source);
        if (count == 0) {
            throw new IllegalArgumentException(DaoErrorMessages.ELEMENT_WITH_SUCH_ID_ISNT_EXIST);
        }
    }


    private class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            Employee employee = new Employee();
            employee.setId(resultSet.getInt(ID));
            employee.setDate_of_birth(resultSet.getString(DATE_OF_BIRTH));
            employee.setFirst_name(resultSet.getString(FIRST_NAME));
            employee.setLast_name(resultSet.getString(LAST_NAME));
            employee.setGender(resultSet.getString(GENDER));
            employee.setJob_title(resultSet.getString(JOB_TITLE));
            employee.setDepartment_id(resultSet.getInt(DEPARTMENT_ID));
            return employee;
        }
    }
}
