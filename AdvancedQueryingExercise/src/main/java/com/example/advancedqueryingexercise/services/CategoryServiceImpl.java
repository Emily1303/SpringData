package com.example.advancedqueryingexercise.services;

import com.example.advancedqueryingexercise.entities.Category;
import com.example.advancedqueryingexercise.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
