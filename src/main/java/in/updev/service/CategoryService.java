package in.updev.service;

import in.updev.model.Category;
import in.updev.model.SubCategory;
import in.updev.repository.CategoryRepository;
import in.updev.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public void createCategory(String categoryName, String subCategoryName) {
        Category category = new Category();
        category.setName(categoryName);
        categoryRepository.save(category);

        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryName);
        subCategory.setCategory(category);
        subCategoryRepository.save(subCategory);
    }

    public void modifyCategory(Long categoryId, String newCategoryName, String newSubCategoryName) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(newCategoryName);
            categoryRepository.save(category);

            List<SubCategory> subCategories = subCategoryRepository.findByCategoryId(categoryId);
            for (SubCategory subCategory : subCategories) {
                subCategory.setName(newSubCategoryName);
                subCategoryRepository.save(subCategory);
            }
        }
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}