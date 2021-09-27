package sds.rst.controller;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sds.rst.data.Role;
import sds.rst.repo.RoleRepo;

import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {
    private final RoleRepo roleRepo;

    public RoleController(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("roles", roleRepo.findAll());
        return "role";
    }

    @PostMapping
    public String add(
            @RequestParam String name,
            @RequestParam String salary,
            Model model
    ) {
        Role role = new Role(name, Integer.valueOf(salary));

        roleRepo.save(role);

        model.addAttribute("roles", roleRepo.findAll());
        return "role";
    }

    @PutMapping("{id}")
    public String update(
            @PathVariable("id") Role role,
            @RequestBody Map<String, String> data,
            Model model
    ) {
        role.setName(data.get("name"));
        role.setSalary(Integer.valueOf(data.get("salary")));

        roleRepo.save(role);

        model.addAttribute("roles", roleRepo.findAll());
        return "role";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Role role, Model model) {
        roleRepo.delete(role);

        model.addAttribute("roles", roleRepo.findAll());
        return "role";
    }
}
