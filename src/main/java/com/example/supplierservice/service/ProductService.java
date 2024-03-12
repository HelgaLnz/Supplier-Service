package com.example.supplierservice.service;

import com.example.supplierservice.entity.Category;
import com.example.supplierservice.entity.Product;
import com.example.supplierservice.repository.ProductRepository;
import com.example.supplierservice.request.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryService categoryService;

  public void create(ProductDto.Full request) {
    Category category = categoryService.get(request.getCategoryId());
    productRepository.save(Product.builder()
      .name(request.getName())
      .description(request.getDescription())
      .price(request.getPrice())
      .category(category)
      .build());
  }

  public Page<ProductDto.Display> getAll(Pageable pageable) {
    return productRepository.findAllProducts(pageable)
      .map(product -> ProductDto.Display.builder()
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .category(product.getCategory())
        .build()
      );
  }

  public ProductDto.Display getById(Integer id) {
    return productRepository.findById(id).map(product -> ProductDto.Display.builder()
      .name(product.getName())
      .description(product.getDescription())
      .price(product.getPrice())
      .category(product.getCategory())
      .build()
    ).orElseThrow();
  }

  public ProductDto.Full change(ProductDto.Full productNew, Integer id) {
    Category category = categoryService.get(productNew.getCategoryId());

    Product productResult = productRepository.findById(id)
      .map(product -> {
        product.setName(productNew.getName());
        product.setDescription(productNew.getDescription());
        product.setPrice(productNew.getPrice());
        product.setCategory(category);
        return productRepository.save(product);
      }).orElseGet(() -> productRepository.save(Product.builder()
        .name(productNew.getName())
        .description(productNew.getDescription())
        .price(productNew.getPrice())
        .category(category)
        .id(id)
        .build())
      );

    return ProductDto.Full.builder()
      .name(productResult.getName())
      .description(productResult.getDescription())
      .price(productResult.getPrice())
      .categoryId(productResult.getCategory().getId())
      .build();
  }

  public ProductDto.Full delete(Integer id) {
    Product productDeleted = productRepository.findById(id).orElseThrow();
    productRepository.deleteById(id);
    return ProductDto.Full.builder()
      .name(productDeleted.getName())
      .description(productDeleted.getDescription())
      .price(productDeleted.getPrice())
      .categoryId(productDeleted.getCategory().getId())
      .build();
  }
}
