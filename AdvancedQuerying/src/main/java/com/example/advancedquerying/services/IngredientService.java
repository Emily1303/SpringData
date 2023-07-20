package com.example.advancedquerying.services;

import com.example.advancedquerying.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {

    List<Ingredient> findIngredientsWhichNameStartsWith(Character letter);

    List<Ingredient> findIngredientsWhichNameIsInTheGivenCollection(List<String> names);

    void deleteIngredientByName(String name);

    void updateIngredientsPrice(BigDecimal number);

    void updateIngredientsPriceWhichName(List<String> names, BigDecimal number);

}
