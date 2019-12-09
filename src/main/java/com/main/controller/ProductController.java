package com.main.controller;

import com.main.model.Product;
import com.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> getAll() {
        return productService.getAllProduct();
    }

    @GetMapping("/id")
    public ResponseEntity<Product> getById(@PathVariable("id") Integer id) {
        Optional<Product> optional = productService.getfindById(id);
        if (!optional.isPresent()) {
            return new ResponseEntity<Product>(optional.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(optional.get(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, UriComponentsBuilder builder) {
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> updateUser(@PathVariable("id") Integer id, @RequestBody Product product) {
        Optional<Product> currentPro = productService.getfindById(id);

        if (!currentPro.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        currentPro.get().setCategoryId(product.getCategoryId());
        currentPro.get().setAmount(product.getAmount());
        currentPro.get().setDescription(product.getDescription());
        currentPro.get().setPercentSale(product.getPercentSale());
        currentPro.get().setPriceSale(product.getPriceSale());
        currentPro.get().setProductImage(product.getProductImage());
        currentPro.get().setProductName(product.getProductName());
        currentPro.get().setProductPrice(product.getProductPrice());
        currentPro.get().setStatus(product.getStatus());

        productService.save(currentPro.get());
        return new ResponseEntity<>(currentPro.get(), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(
            @PathVariable("id") Integer id) {
        Optional<Product> product = productService.getfindById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(product.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
