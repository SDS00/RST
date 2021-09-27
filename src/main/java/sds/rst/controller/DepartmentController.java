package sds.rst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sds.rst.data.Department;
import sds.rst.data.Employee;
import sds.rst.repo.DepartmentRepo;
import sds.rst.repo.EmployeeRepo;
import sds.rst.repo.RoleRepo;

import java.util.Map;

@Controller
@RequestMapping("department")
public class DepartmentController {
    private final DepartmentRepo departmentRepo;
    private final EmployeeRepo employeeRepo;
    private final RoleRepo roleRepo;

    public DepartmentController(DepartmentRepo departmentRepo, EmployeeRepo employeeRepo, RoleRepo roleRepo) {
        this.departmentRepo = departmentRepo;
        this.employeeRepo = employeeRepo;
        this.roleRepo = roleRepo;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("departments", departmentRepo.findAll());
        return "department";
    }

    @GetMapping("{id}")
    public String getOneDepartment(@PathVariable("id") Department department, Model model) {
        Iterable<Employee> employees = employeeRepo.getByDepartment(department);

        model.addAttribute("department", department);
        model.addAttribute("employees", employees);

        model.addAttribute("departments", departmentRepo.findAll());
        model.addAttribute("roles", roleRepo.findAll());
        return "specific_department";
    }

    @PostMapping
    public String add(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String email,
            Model model
    ) {
        Department department = new Department(name, phone, email);

        departmentRepo.save(department);

        model.addAttribute("departments", departmentRepo.findAll());
        return "department";
    }

    @PutMapping("{id}")
    public String update(
            @PathVariable("id") Department department,
            @RequestBody Map<String, String> data,
            Model model
    ) {
        department.setName(data.get("name"));
        department.setPhone(data.get("phone"));
        department.setEmail(data.get("email"));

        departmentRepo.save(department);

        model.addAttribute("departments", departmentRepo.findAll());
        return "department";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Department department, Model model) {
        departmentRepo.delete(department);

        model.addAttribute("departments", departmentRepo.findAll());
        return "department";
    }
}
