package sds.rst.controller;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sds.rst.data.Department;
import sds.rst.data.Employee;
import sds.rst.data.Role;
import sds.rst.repo.DepartmentRepo;
import sds.rst.repo.EmployeeRepo;
import sds.rst.repo.RoleRepo;

import java.util.Map;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeRepo employeeRepo;
    private final DepartmentRepo departmentRepo;
    private final RoleRepo roleRepo;

    public EmployeeController(EmployeeRepo employeeRepo, DepartmentRepo departmentRepo, RoleRepo roleRepo) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
        this.roleRepo = roleRepo;
    }

    @PostMapping
    public String add(
            @RequestParam("department_id") Department department,
            @RequestParam("role_id") Role role,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String email,
            Model model
    ) {
        Employee employee = new Employee(name, phone, email, department, role);

        employeeRepo.save(employee);

        Iterable<Employee> employees = employeeRepo.getByDepartment(department);

        model.addAttribute("department", department);
        model.addAttribute("employees", employees);
        return "redirect:department/" + department.getId();
    }

    @PutMapping("{id}")
    public String update(
            @PathVariable("id") Employee employee,
            @RequestBody Map<String, String> data,
            Model model
    ) {
        Department department = departmentRepo.getById(Long.valueOf(data.get("department_id")));
        Role role = roleRepo.getById(Long.valueOf(data.get("role_id")));

        employee.setName(data.get("name"));
        employee.setPhone(data.get("phone"));
        employee.setEmail(data.get("email"));
        employee.setDepartment(department);
        employee.setRole(role);

        employeeRepo.save(employee);

        Iterable<Employee> employees = employeeRepo.getByDepartment(department);

        model.addAttribute("department", department);
        model.addAttribute("employees", employees);
        return "redirect:department/" + department.getId();
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Employee employee, Model model) {
        Department department = employee.getDepartment();

        employeeRepo.delete(employee);

        Iterable<Employee> employees = employeeRepo.getByDepartment(department);

        model.addAttribute("department", department);
        model.addAttribute("employees", employees);
        return "redirect:department/" + department.getId();
    }
}
