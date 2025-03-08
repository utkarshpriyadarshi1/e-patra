package in.updev.controller;

import in.updev.model.Category;
import in.updev.edastavej.model.File;
import in.updev.model.SubCategory;
import in.updev.service.CategoryService;
import com.updevlogics.edastavej.service.FileService;
import in.updev.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private FileService fileService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/search")
    public String showSearchForm(Model model,
                                 @RequestParam(value = "fileName", required = false) String fileName,
                                 @RequestParam(value = "description", required = false) String description,
                                 @RequestParam(value = "categoryId", required = false) Long categoryId,
                                 @RequestParam(value = "subCategoryId", required = false) Long subCategoryId,
                                 @RequestParam(value = "startDate", required = false) String startDate,
                                 @RequestParam(value = "endDate", required = false) String endDate) {
        List<File> files = fileService.searchFiles(fileName, description, categoryId, subCategoryId, startDate, endDate);
        List<Category> categories = categoryService.getAllCategories();
        List<SubCategory> subCategories = subCategoryService.getAllSubCategories();
        model.addAttribute("files", files);
        model.addAttribute("categories", categories);
        model.addAttribute("subCategories", subCategories);
        return "search";
    }
}