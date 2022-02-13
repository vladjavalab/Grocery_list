package controller;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping(value = "/product")
    public ResponseEntity<Void> create(@RequestBody Product product) {
        productService.create(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/product")
    public ResponseEntity<?> read() {
        final List<Product> productList = productService.readAll();

        return makeResponse(productList, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") long id) {
        final Product product = productService.read(id);

        return makeResponse(product, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        productService.delete(id);
        return  makeResponse(true, HttpStatus.NOT_MODIFIED);
    }

    private ResponseEntity<?> makeResponse(Object data, HttpStatus statusOnFail){
        return data != null
                ? new ResponseEntity<>(data,HttpStatus.OK)
                : new ResponseEntity<>(statusOnFail);
    }

    private ResponseEntity<?> makeResponse(List<Product> collection, HttpStatus statusOnFail){
        return collection != null && !collection.isEmpty()
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(statusOnFail);
    }
}
