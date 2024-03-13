package com.example.supplierservice.repository;

import com.example.supplierservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  @Query(value = "select p from Product p join fetch p.category category")
  Page<Product> findAllProducts(Pageable pageable);

  @Query("select (count(p) > 0) from Product p where p.name = ?1")
  Boolean existsProductByName(String name);
}
