package com.sysoevvladislav.GroceryList.service;


import com.sysoevvladislav.GroceryList.model.ProductList;

import java.util.List;

public interface ListService {

    void create(ProductList product);

    List<ProductList> readAll();

    ProductList read(long id);

    void delete(long id);

    boolean addToList(Long productId, Long listId);
}
