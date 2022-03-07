package com.sysoevvladislav.GroceryList.service;

import com.sysoevvladislav.GroceryList.model.Product;
import org.springframework.stereotype.Service;
import com.sysoevvladislav.GroceryList.repository.ProductsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductsRepository repository;

    public ProductServiceImpl(ProductsRepository repository) {
        this.repository = repository;
    }


    @Override
    public void create(Product product) {
    repository.save(product);
    }

    @Override
    public List<Product> readAll() {
        List<Product> result = new ArrayList<>();

        for(Product product : repository.findAll()){
            result.add(product);
        }

        return result;
    }

    @Override
    public Product read(long id) {
        return repository.getById(id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
