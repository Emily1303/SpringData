package com.example.advancedquerying.repositories;

import com.example.advancedquerying.entities.Shampoo;
import com.example.advancedquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findBySizeOrderById(Size size);

    List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, Long id);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    int countByPriceLessThan(BigDecimal price);

    @Query("SELECT DISTINCT s FROM Shampoo AS s " +
            "INNER JOIN s.ingredients AS i " +
            "WHERE i.name IN :names")
    List<Shampoo> findShampoosByIngredients(List<String> names);

    @Query("SELECT s FROM Shampoo AS s " +
            "WHERE size(s.ingredients) < :number ")
    List<Shampoo> findShampoosWithIngredientsSizeLessThan(Integer number);

}
