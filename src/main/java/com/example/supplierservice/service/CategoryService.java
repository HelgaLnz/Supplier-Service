package com.example.supplierservice.service;

import com.example.supplierservice.entity.Category;
import com.example.supplierservice.exception.ObjectAlreadyExistsException;
import com.example.supplierservice.exception.ObjectNotFoundException;
import com.example.supplierservice.repository.CategoryRepository;
import com.example.supplierservice.request.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryDto.Display getById(Integer id) {
    return categoryRepository.findById(id).map(category -> CategoryDto.Display.builder()
      .name(category.getName())
      .build()).orElseThrow(() -> new ObjectNotFoundException("Category with " + id + " not found."));
  }

  public void create(CategoryDto.Display category) {
    if (!categoryRepository.existsByName(category.name())) {
      categoryRepository.save(Category.builder()
        .name(category.name())
        .build());
    } else
      throw new ObjectAlreadyExistsException("Category already exists with name '" + category.name() + "'");
  }

  public Page<CategoryDto.Display> getAll(Pageable pageable) {
    return categoryRepository.findAllCategories(pageable)
      .map(category -> CategoryDto.Display.builder()
        .name(category.getName())
        .build());
  }

  public CategoryDto.Display change(CategoryDto.Display categoryNew, Integer id) {
    if (!categoryRepository.existsByName(categoryNew.name())) {

      Category categoryResult = categoryRepository.findById(id)
        .map(category -> {
          category.setName(categoryNew.name());
          return categoryRepository.save(category);
        }).orElse(Category.builder()
          .name(categoryNew.name())
          .build()
        );

      return CategoryDto.Display.builder()
        .name(categoryResult.getName())
        .build();
    } else
      throw new ObjectAlreadyExistsException("Category already exists with name '" + categoryNew.name() + "'");

  }

  public CategoryDto.Display delete(Integer id) {
    Category categoryDeleted = categoryRepository.findById(id)
      .orElseThrow(() -> new ObjectNotFoundException("Category with " + id + " not found."));
    categoryRepository.deleteById(id);

    return CategoryDto.Display.builder()
      .name(categoryDeleted.getName())
      .build();
  }

  public Category getByIdEntity(Integer id) {
    return categoryRepository.findById(id)
      .orElseThrow(() -> new ObjectNotFoundException("Category with " + id + " not found."));
  }
}
