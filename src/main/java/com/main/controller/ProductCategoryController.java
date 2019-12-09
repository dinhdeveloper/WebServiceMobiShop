package com.main.controller;


import com.main.model.ProductCategory;
import com.main.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService proCateService;
    @GetMapping("/list")
    public List<ProductCategory> getAll(){
        return proCateService.getAllProCate();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getCateById(@PathVariable("id") Integer id) {
        Optional<ProductCategory> proCate = proCateService.findById(id);

        if (!proCate.isPresent()) {
            return new ResponseEntity<>(proCate.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(proCate.get(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory, UriComponentsBuilder builder) {
        proCateService.save(productCategory);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/products/{id}")
                .buildAndExpand(productCategory.getCategoryId()).toUri());
        return new ResponseEntity<>(productCategory, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductCategory> updateUser(@PathVariable("id") Integer id, @RequestBody ProductCategory productCategory) {
        Optional<ProductCategory> currentCate= proCateService.findById(id);

        if (!currentCate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentCate.get().setCategoryName(productCategory.getCategoryName());
        currentCate.get().setCategoryImage(productCategory.getCategoryImage());
        currentCate.get().setPromotionId(productCategory.getPromotionId());

        proCateService.save(currentCate.get());
        return new ResponseEntity<>(currentCate.get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductCategory> deleteProduct(
            @PathVariable("id") Integer id) {
        Optional<ProductCategory> productCategory = proCateService.findById(id);
        if (!productCategory.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        proCateService.remove(productCategory.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
