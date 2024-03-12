package com.example.supplierservice.request;

import com.example.supplierservice.entity.Category;
import lombok.Builder;
import lombok.Data;

public class ProductDto {

  @Data
  @Builder
  public static class Full {
    private String name;
    private String description;
    private Double price;
    private Integer categoryId;
  }

  @Data
  @Builder
  public static class Display {
    private String name;
    private String description;
    private Double price;
    private Category category;
  }
}
