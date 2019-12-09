package com.main.service;

import com.main.model.Product;
import com.main.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository proRes;
    public List<Product> getAllProduct(){
        List<Product> productList = proRes.findAll();
        return productList;
    }

    public Optional<Product> getfindById(Integer id){
        return proRes.findById(id);
    }
    public void save(Product product){
        proRes.save(product);
    }
    public void remove(Product product){
        proRes.delete(product);
    }
}
