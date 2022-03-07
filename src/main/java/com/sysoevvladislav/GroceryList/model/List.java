package com.sysoevvladislav.GroceryList.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy="list", fetch = FetchType.EAGER)
    private java.util.List<Product> productList;



    //Getters and setters
    public java.util.List<Product> getProductList() {
        return productList;
    }

    public void setProductList(java.util.List<Product> productList) {
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEmpty(){
        return productList == null || productList.isEmpty();
    }

    public int getSize(){
       if(productList == null) return 0;
       return productList.size();
    }

    public boolean addProduct(Product product){
        if(productList == null) productList = new ArrayList<>();
        return productList.add(product);
    }
}
