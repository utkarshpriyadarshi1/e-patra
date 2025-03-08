package in.updev.edastavej.controller;

import in.updev.edastavej.model.Role;
import in.updev.edastavej.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String listRoles(Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "roles";
    }

    @GetMapping("/new")
    public String showRoleForm(Model model) {
        model.addAttribute("role", new Role());
        return "role_form";
    }

    @GetMapping("/edit/{id}")
    public String showEditRoleForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", roleService.getRoleById(id));
        return "role_form";
    }

    @PostMapping("/save")
    public String saveRole(@ModelAttribute("role") Role role) {
        roleService.saveRole(role);
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return "redirect:/roles";
    }
}