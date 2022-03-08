package com.sysoevvladislav.GroceryList.repository;

import com.sysoevvladislav.GroceryList.model.ProductList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends CrudRepository<ProductList, Long> {
    ProductList getById(Long id);

    void deleteById(Long id);
}
