package com.example.advancedquerying.repositories;

import com.example.advancedquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByNameStartingWith(Character letter);
    List<Ingredient> findByNameInOrderByPrice(List<String> names);

    @Modifying
    @Transactional
    @Query("DELETE FROM Ingredient AS i WHERE i.name = :name")
    void deleteIngredientByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient AS i " +
            "SET i.price = i.price + (i.price * :number)")
    void increaseIngredientsPriceBy10Percent(BigDecimal number);

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient AS i " +
            "SET i.price = i.price + (i.price * :number) " +
            "WHERE i.name IN :names")
    void increaseIngredientsPriceWhichName(List<String> names, BigDecimal number);

}
