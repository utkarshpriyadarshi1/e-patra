package in.updev.controller;

import in.updev.model.Category;
import in.updev.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/createCategory")
    public String createCategory(@RequestParam("categoryName") String categoryName,
                                 @RequestParam("subCategoryName") String subCategoryName,
                                 Model model) {
        categoryService.createCategory(categoryName, subCategoryName);
        model.addAttribute("message", "Category and Subcategory created successfully!");
        return "createCategory";
    }

    @PostMapping("/admin/modifyCategory")
    public String modifyCategory(@RequestParam("categoryId") Long categoryId,
                                 @RequestParam("newCategoryName") String newCategoryName,
                                 @RequestParam("newSubCategoryName") String newSubCategoryName,
                                 Model model) {
        categoryService.modifyCategory(categoryId, newCategoryName, newSubCategoryName);
        model.addAttribute("message", "Category and Subcategory modified successfully!");
        return "modifyCategory";
    }

    @PostMapping("/admin/deleteCategory")
    public String deleteCategory(@RequestParam("categoryId") Long categoryId, Model model) {
        categoryService.deleteCategory(categoryId);
        model.addAttribute("message", "Category deleted successfully!");
        return "modifyCategory";
    }

    @PostMapping("/admin/getCategories")
    public String getCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categoryList";
    }
}