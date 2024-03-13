package com.example.supplierservice.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public class CategoryDto {

  @Builder
  public record Display(
    @NotBlank(message = "Name cannot be null or whitespace")
    @Size(min = 5, max = 100, message = "Name must be between 5 and 100 characters")
    String name
  ) {
  }
}
