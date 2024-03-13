package com.example.supplierservice.controller;

import com.example.supplierservice.request.ProductDto;
import com.example.supplierservice.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

  private final ProductService productService;

  @PostMapping("/products")
  public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto.Full request) {
    productService.create(request);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/products")
  public ResponseEntity<Page<ProductDto.Display>> getAllProducts(
    @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
    @RequestParam(value = "limit", defaultValue = "5") @Min(0) @Max(100) Integer limit
  ) {
    return ResponseEntity.ok(productService.getAll(PageRequest.of(offset, limit)));
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<ProductDto.Display> getProductById(@PathVariable Integer id) {
    return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<?> editProduct(@Valid @RequestBody ProductDto.Full request, @PathVariable Integer id) {
    return new ResponseEntity<>(productService.change(request, id), HttpStatus.OK);
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
    return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
  }
}
