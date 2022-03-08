package com.sysoevvladislav.GroceryList.service;

import com.sysoevvladislav.GroceryList.model.Product;
import com.sysoevvladislav.GroceryList.model.ProductList;
import com.sysoevvladislav.GroceryList.repository.ListRepository;
import com.sysoevvladislav.GroceryList.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListServiceImpl implements ListService {

    private final ListRepository listRepository;
    private final ProductsRepository productsRepository;

    @Value("${listServiceImpl.maxProducts}")
    int maxProductsInList;

    public ListServiceImpl(ListRepository listRepository, ProductsRepository productsRepository) {
        this.listRepository = listRepository;
        this.productsRepository = productsRepository;
    }

    @Override
    public void create(ProductList productList) {
        listRepository.save(productList);
    }

    @Override
    public List<ProductList> readAll() {
       List<ProductList> result = new ArrayList<>();

        for (ProductList productList : listRepository.findAll()) {
            result.add(productList);
        }

        return result;
    }

    @Override
    public ProductList read(long id) {
        return listRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        listRepository.deleteById(id);
    }

    @Override
    public boolean addToList(Long productId, Long listId) {
        ProductList productList = listRepository.getById(listId);

        if(productList.getSize() == maxProductsInList) return false;

        Product product = productsRepository.getById(productId);

        productList.addProduct(product);

        listRepository.save(productList);

        return true;
    }
}
