package com.example.advancedquerying.services;

import com.example.advancedquerying.entities.Shampoo;
import com.example.advancedquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {

    List<Shampoo> findShampoosBySize(Size size);

    List<Shampoo> findShampoosBySizeOrLabel(Size size, Long id);

    List<Shampoo> findShampoosByPriceGreaterThanGiven(BigDecimal price);

    int countShampoosWithPriceLowerThanGiven(BigDecimal price);

    List<Shampoo> findAllShampoosWithIngredientsNames(List<String> names);

    List<Shampoo> findAllShampoosWithIngredientsSizeLessThanGivenNumber(Integer number);
}
