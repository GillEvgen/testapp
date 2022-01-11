package com.gillevgenii.testapp.webapp.listener;

import com.gilevgenii.testapp.service.api.EmployeeService;
import com.gillevgenii.testapp.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employees")
    public final String getEmployees(final Model model) {
        LOGGER.debug("findAll({})", model);
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employeePage";
    }

//    @GetMapping(value = "/employee/{id}")
//    public final String getEmployeeById(@PathVariable final Integer id, final Model model) {
//        LOGGER.debug("gotoEditEmployeePage({},{})", id, model);
//        Employee employee = employeeService.getEmployeeById(id);
//        model.addAttribute("employee", employee);
//        return "employeePage";
//    }

    @GetMapping("/employee/add")
    public String getEmployeeForm(Model model) {
        LOGGER.debug("getEmployeeForm()");
        List<Employee> employeeList = employeeService.getAllEmployees();
        model.addAttribute("employeeList", employeeList);
        return "employeeAdd";
    }

    @PostMapping(value = "/employee/add")
    public String addEmployee(@Valid final Employee employee,
                              final BindingResult result,
                              final Model model) {
        LOGGER.debug("addEmployee(): " + employee);
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            model.addAttribute("employee", employee);
            return "employeeAdd";
        } else {
            List<Employee> resultEmployee = employeeService.addEmployee(employee);
            LOGGER.debug("addEmployee({})", resultEmployee);
            return "redirect:/employees";
        }
    }

    @GetMapping(value = "/employee/edit")
    public String employeeEditForm(@RequestParam("id") Integer id, Model model) {
        LOGGER.debug("employeeEditForm, ID={}", id);
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employeeEdit";
    }


    @PostMapping(value = "/employee/edit")
    public final String updateEmployee(@Valid final Employee employee,
                                       final BindingResult result,
                                       final Model model) {

        LOGGER.debug("updateEmployee({}, {})", employee);

        if (result.hasErrors()) {
            model.addAttribute("employee", employee);
            model.addAttribute("isEdit", true);
            return "employeeEdit";
        } else {
            employeeService.updateEmployee(employee);
            return "redirect:/employees";
        }
    }
//    @RequestMapping(value = "/employee/delete", method = RequestMethod.GET)
//    public String employeeDeletePage(
//            @RequestParam("id") Integer id,
//            Model model) {
//        LOGGER.debug("employee delete page, id={}", id);
//        Employee employee = employeeService.getEmployeeById(id);
//        model.addAttribute("employee", employee);
//        return "employeeDelete";
//    }
//
//    @RequestMapping(value = "/employee/delete", method = RequestMethod.POST)
//    public String employeeDelete(
//            @RequestParam("id") Integer id,
//            Model model) {
//        LOGGER.debug("employee delete");
//        employeeService.deleteEmployeeById(id);
//        List empList = employeeService.getAllEmployees();
//        model.addAttribute("empList", empList);
//        return "redirect:/employees";
//    }
}
