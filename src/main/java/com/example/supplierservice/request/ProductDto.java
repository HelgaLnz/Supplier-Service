package com.example.supplierservice.request;

import com.example.supplierservice.entity.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

public class ProductDto {

  @Data
  @Builder
  public static class Full {

    @NotBlank(message = "Name cannot be null or whitespace")
    @Size(min = 5, max = 100, message = "Name must be between 5 and 100 characters")
    private String name;

    @NotBlank(message = "Description cannot be null or whitespace")
    @Size(min = 50, max = 255, message = "Description must be between 50 and 255 characters")
    private String description;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "100.0", message = "Price should not be less than 100.0")
    private Double price;

    @NotNull(message = "Category id cannot be null")
    private Integer categoryId;
  }

  @Data
  @Builder
  public static class Display {

    @NotBlank(message = "Name cannot be null or whitespace")
    @Size(min = 5, max = 100, message = "Name must be between 5 and 100 characters")
    private String name;

    @NotBlank(message = "Description cannot be null or whitespace")
    @Size(min = 50, max = 255, message = "Description must be between 50 and 255 characters")
    private String description;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "100.0", message = "Price should not be less than 100.0")
    private Double price;

    @NotNull(message = "Category id cannot be null")
    private Category category;
  }
}
