package com.example.supplierservice.service;

import com.example.supplierservice.entity.Category;
import com.example.supplierservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public Category get(Integer categoryId) {
    Optional<Category> category = categoryRepository.findById(categoryId);
    return category.orElseThrow();
  }
}
