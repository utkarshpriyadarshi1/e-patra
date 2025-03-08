package in.updev.controller;

import in.updev.model.SubCategory;
import in.updev.service.CategoryService;
import in.updev.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subcategories")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listSubCategories(Model model) {
        model.addAttribute("subcategories", subCategoryService.getAllSubCategories());
        return "subcategories";
    }

    @GetMapping("/new")
    public String showSubCategoryForm(Model model) {
        model.addAttribute("subcategory", new SubCategory());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "subcategory_form";
    }

    @GetMapping("/edit/{id}")
    public String showEditSubCategoryForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("subcategory", subCategoryService.getSubCategoryById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "subcategory_form";
    }

    @PostMapping("/save")
    public String saveSubCategory(@ModelAttribute("subcategory") SubCategory subCategory) {
        subCategoryService.saveSubCategory(subCategory);
        return "redirect:/subcategories";
    }

    @GetMapping("/delete/{id}")
    public String deleteSubCategory(@PathVariable("id") Long id) {
        subCategoryService.deleteSubCategory(id);
        return "redirect:/subcategories";
    }
}