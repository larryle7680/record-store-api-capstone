package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.repository.CategoryRepository;
import org.yearup.repository.ProductRepository;

import java.util.List;

@Service
public class CategoryService
{
    private final CategoryRepository categoryRepository;
    ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository)
    {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> getAllCategories()
    {
        // get all categories
        return categoryRepository.findAll();
    }

    public Category getById(int categoryId)
    {
        // get category by id
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Can't find category"));
    }

    public Category create(Category category)
    {
        // create a new category
        return categoryRepository.save(category);
    }

    public Category update(int categoryId, Category category)
    {
        //Make sure the CategoryID exists then provide a variable for it
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Can't find Category"));

        //find the existing category and use .setName to update the name
        existingCategory.setName(category.getName());

        // update category and return the updated category
        return categoryRepository.save(category);
    }

    public void delete(int categoryId)
    {
        // delete category
        categoryRepository.deleteById(categoryId);
    }

    public List<Product> listByCategoryId(int categoryId) {


        return productRepository.findByCategoryId(categoryId);
    }
}
