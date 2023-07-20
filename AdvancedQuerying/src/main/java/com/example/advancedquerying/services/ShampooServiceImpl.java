package com.example.advancedquerying.services;

import com.example.advancedquerying.entities.Shampoo;
import com.example.advancedquerying.entities.Size;
import com.example.advancedquerying.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findShampoosBySize(Size size) {
        return shampooRepository.findBySizeOrderById(size);
    }

    @Override
    public List<Shampoo> findShampoosBySizeOrLabel(Size size, Long id) {
        return shampooRepository.findBySizeOrLabelIdOrderByPriceAsc(size, id);
    }

    @Override
    public List<Shampoo> findShampoosByPriceGreaterThanGiven(BigDecimal price) {
        return shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public int countShampoosWithPriceLowerThanGiven(BigDecimal price) {
        return shampooRepository.countByPriceLessThan(price);
    }

    @Override
    public List<Shampoo> findAllShampoosWithIngredientsNames(List<String> names) {
        return shampooRepository.findShampoosByIngredients(names);
    }

    @Override
    public List<Shampoo> findAllShampoosWithIngredientsSizeLessThanGivenNumber(Integer number) {
        return shampooRepository.findShampoosWithIngredientsSizeLessThan(number);
    }


}
