package com.example.advancedquerying.services;

import com.example.advancedquerying.entities.Ingredient;
import com.example.advancedquerying.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findIngredientsWhichNameStartsWith(Character letter) {
        return ingredientRepository.findByNameStartingWith(letter);
    }

    @Override
    public List<Ingredient> findIngredientsWhichNameIsInTheGivenCollection(List<String> names) {
        return ingredientRepository.findByNameInOrderByPrice(names);
    }

    @Override
    public void deleteIngredientByName(String name) {
        ingredientRepository.deleteIngredientByName(name);
    }

    @Override
    public void updateIngredientsPrice(BigDecimal number) {
        ingredientRepository.increaseIngredientsPriceBy10Percent(number);
    }

    @Override
    public void updateIngredientsPriceWhichName(List<String> names, BigDecimal number) {
        ingredientRepository.increaseIngredientsPriceWhichName(names, number);
    }

}
