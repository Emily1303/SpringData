package com.example.advancedquerying;

import com.example.advancedquerying.entities.Size;
import com.example.advancedquerying.services.IngredientService;
import com.example.advancedquerying.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private ShampooService shampooService;

    private IngredientService ingredientService;

    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {

        shampooService.findShampoosBySize(Size.MEDIUM).
                forEach(shampoo ->
                    System.out.println(shampoo.printShampoo()));

        shampooService.findShampoosBySizeOrLabel(Size.MEDIUM, 10L)
                .forEach(shampoo -> System.out.println(shampoo.printShampoo()));

        shampooService.findShampoosByPriceGreaterThanGiven(BigDecimal.valueOf(5))
                .forEach(shampoo -> System.out.println(shampoo.printShampoo()));

        ingredientService.findIngredientsWhichNameStartsWith('M')
                .forEach(ingredient -> System.out.println(ingredient.printName()));

        ingredientService
                .findIngredientsWhichNameIsInTheGivenCollection(List.of("Lavender", "Herbs", "Apple"))
                .forEach(ingredient -> System.out.println(ingredient.printName()));

        int count = shampooService.countShampoosWithPriceLowerThanGiven(BigDecimal.valueOf(8.50));
        System.out.println(count);

        shampooService.findAllShampoosWithIngredientsNames(List.of("Berry", "Mineral-Collagen"))
                .forEach(shampoo -> System.out.println(shampoo.getBrand()));

        shampooService.findAllShampoosWithIngredientsSizeLessThanGivenNumber(2)
                .forEach(shampoo -> System.out.println(shampoo.getBrand()));

        ingredientService.deleteIngredientByName("Apple");

        ingredientService.updateIngredientsPrice(BigDecimal.valueOf(0.10));

        ingredientService
                .updateIngredientsPriceWhichName(List.of("Lavender", "Raspberry", "Berry"),
                        BigDecimal.valueOf(0.10));

    }
}
