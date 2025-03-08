package in.updev.controller;

import in.updev.edastavej.model.File;
import com.updevlogics.edastavej.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to e-Dastavej!");
        return "index";
    }

    @GetMapping("/user/login")
    public String userLogin() {
        return "user_login";
    }

    @GetMapping("/admin/login")
    public String adminLogin() {
        return "admin_login";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard(Model model, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal); // Implement this method to get user ID from Principal
        List<File> recentFiles = fileService.getRecentFilesByUser(userId);
        model.addAttribute("recentFiles", recentFiles);
        return "user_dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin_dashboard";
    }

    private Long getUserIdFromPrincipal(Principal principal) {
        // Implement this method to retrieve the user ID from the authenticated user's Principal
        // For instance, you can fetch the user by username and then get the user ID
        // Example:
        // User user = userRepository.findByUsername(principal.getName());
        // return user.getId();
        return null; // Replace with actual implementation
    }
}