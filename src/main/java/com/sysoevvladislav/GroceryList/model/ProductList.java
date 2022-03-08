package com.sysoevvladislav.GroceryList.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "list", fetch = FetchType.EAGER)
    private List<Product> productList;


    public int getSize() {
        if(productList == null) return 0;
        return productList.size();
    }

    public boolean addProduct(Product product) {
        if(productList == null) productList = new ArrayList<>();
        return productList.add(product);
    }
}
