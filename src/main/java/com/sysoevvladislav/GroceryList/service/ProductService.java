package com.sysoevvladislav.GroceryList.service;


import com.sysoevvladislav.GroceryList.model.Product;

import java.util.List;

public interface ProductService {

    void create(Product product);

    List<Product> readAll();

    Product read(long id);

    void delete(long id);
}
