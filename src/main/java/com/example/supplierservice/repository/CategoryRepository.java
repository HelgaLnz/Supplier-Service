package com.example.supplierservice.repository;

import com.example.supplierservice.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

  @Query("select (count(c) > 0) from Category c where c.name = ?1")
  Boolean existsByName(String name);

  @Query(value = "select c from Category c")
  Page<Category> findAllCategories(Pageable pageable);
}
