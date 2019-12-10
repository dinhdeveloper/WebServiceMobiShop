package com.main.controller;


import com.main.exception.RecordNotFoundException;
import com.main.model.ProductCategory;
import com.main.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService proCateService;
    @GetMapping("/list")
    public ResponseEntity<List<ProductCategory>> getAllProduct() {
        List<ProductCategory> list = proCateService.getAllProCate();
        return new ResponseEntity<List<ProductCategory>>(list,new HttpHeaders(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getById(@PathVariable("id") Integer id) throws RecordNotFoundException {
        ProductCategory optional = proCateService.findCateById(id);
        return new ResponseEntity<ProductCategory>(optional, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductCategory> createOrUpdateCategory(@Valid @RequestBody ProductCategory product)
            throws RecordNotFoundException {
        ProductCategory updated = proCateService.createOrUpdateProduct(product);
        return new ResponseEntity<ProductCategory>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteCategoryById(@PathVariable("id") Integer id)
            throws RecordNotFoundException {
        proCateService.deleteCategoryById(id);
        return HttpStatus.OK;
    }
}
