package com.sysoevvladislav.GroceryList.controller;

import com.sysoevvladislav.GroceryList.model.ProductList;
import com.sysoevvladislav.GroceryList.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ListController {

    private final ListService listService;

    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }


    @PostMapping(value = "/list")
    public ResponseEntity<Void> create(@RequestBody ProductList productList) {
        listService.create(productList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> read() {
        final List<ProductList> productList = listService.readAll();

        return makeResponse(productList, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") long id) {
        final ProductList productList = listService.read(id);

        return makeResponse(productList, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/list/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        listService.delete(id);
        return makeResponse(true, HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/list")
    public ResponseEntity<Void> addToList(@RequestBody Long productId, @RequestBody Long listId) {
        listService.addToList(productId, listId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private ResponseEntity<?> makeResponse(Object data, HttpStatus statusOnFail) {
        return data != null
                ? new ResponseEntity<>(data, HttpStatus.OK)
                : new ResponseEntity<>(statusOnFail);
    }

    private ResponseEntity<?> makeResponse(List<ProductList> collection, HttpStatus statusOnFail) {
        return collection != null && !collection.isEmpty()
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(statusOnFail);
    }

}

