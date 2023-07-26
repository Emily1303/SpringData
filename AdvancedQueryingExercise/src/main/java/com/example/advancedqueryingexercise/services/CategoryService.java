package com.example.advancedqueryingexercise.services;

import com.example.advancedqueryingexercise.entities.Category;

import java.util.List;

public interface CategoryService {

    boolean isSeed();

    void saveSeed(List<Category> categories);

    Category getRandomCategory();

}
