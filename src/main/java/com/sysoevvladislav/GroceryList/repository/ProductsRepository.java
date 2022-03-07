package com.sysoevvladislav.GroceryList.repository;

import com.sysoevvladislav.GroceryList.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Product, Long> {
    Product getById(Long id);
    void deleteById(Long id);
}
