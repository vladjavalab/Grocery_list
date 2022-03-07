package com.sysoevvladislav.GroceryList.service;

import com.sysoevvladislav.GroceryList.model.Product;
import com.sysoevvladislav.GroceryList.model.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.sysoevvladislav.GroceryList.repository.ListRepository;
import com.sysoevvladislav.GroceryList.repository.ProductsRepository;

import java.util.ArrayList;

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
    public void create(List list) {
        listRepository.save(list);
    }

    @Override
    public java.util.List<List> readAll() {
        java.util.List<List> result = new ArrayList<>();

        for(List list : listRepository.findAll()){
            result.add(list);
        }

        return result;
    }

    @Override
    public List read(long id) {
        return listRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        listRepository.deleteById(id);
    }

    @Override
    public boolean addToList(Long productId, Long listId) {
        List list = listRepository.getById(listId);

        if(list.getSize() == maxProductsInList) return false;

        Product product = productsRepository.getById(productId);

        list.addProduct(product);

        listRepository.save(list);

        return true;
    }
}
