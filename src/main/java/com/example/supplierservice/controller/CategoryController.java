package com.example.supplierservice.controller;

import com.example.supplierservice.request.CategoryDto;
import com.example.supplierservice.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryController {

  public final CategoryService categoryService;

  @PostMapping("/categories")
  public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto.Display category) {
    categoryService.create(category);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/categories")
  public ResponseEntity<?> getAllCategories(
    @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
    @RequestParam(value = "limit", defaultValue = "5") @Min(0) @Max(100) Integer limit
  ) {
    return new ResponseEntity<>(categoryService.getAll(PageRequest.of(offset, limit)), HttpStatus.OK);
  }

  @GetMapping("/categories/{id}")
  public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
    return new ResponseEntity<>(categoryService.getById(id), HttpStatus.OK);
  }

  @PutMapping("/categories/{id}")
  public ResponseEntity<?> editCategory(@Valid @RequestBody CategoryDto.Display category, @PathVariable Integer id) {
    return new ResponseEntity<>(categoryService.change(category, id), HttpStatus.OK);
  }

  @DeleteMapping("/categories/{id}")
  public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
    return new ResponseEntity<>(categoryService.delete(id), HttpStatus.OK);
  }
}
