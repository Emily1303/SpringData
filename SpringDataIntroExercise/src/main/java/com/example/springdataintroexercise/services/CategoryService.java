package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entities.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    boolean isSeed();

    void saveSeed(List<Category> categories);

    Category getRandomCategory();

}
