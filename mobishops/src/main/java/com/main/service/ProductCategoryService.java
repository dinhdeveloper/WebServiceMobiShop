package com.main.service;

import com.main.model.ProductCategory;
import com.main.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository proCateRs;
    public List<ProductCategory> getAllProCate(){
        List<ProductCategory> productCategories = proCateRs.findAll();
        return productCategories;
    }
    public Optional<ProductCategory> findById(Integer id)
    {
        return proCateRs.findById(id);
    }

    public void save(ProductCategory productCategory) {
        proCateRs.save(productCategory);
    }

    public void remove(ProductCategory product) {
        proCateRs.delete(product);
    }
}
