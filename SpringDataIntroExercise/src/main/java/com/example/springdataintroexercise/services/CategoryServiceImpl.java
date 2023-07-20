package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entities.Category;
import org.springframework.stereotype.Service;
import com.example.springdataintroexercise.repositories.CategoryRepository;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean isSeed() {
        if (categoryRepository.count() > 0) {
            return false;
        }

        return true;
    }

    @Override
    public void saveSeed(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }

    @Override
    public Category getRandomCategory() {
        Random random = new Random();

        long randomCategory = random.nextLong(1, categoryRepository.count());
        Optional<Category> byId = categoryRepository.findById(randomCategory);

        return byId.get();

    }
}
