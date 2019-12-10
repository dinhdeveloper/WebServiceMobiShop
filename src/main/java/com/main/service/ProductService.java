package com.main.service;

import com.main.MobiShopUtil;
import com.main.exception.RecordNotFoundException;
import com.main.model.Product;
import com.main.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository proRes;

    //get all
    public List<Product> getAllProduct() {
        List<Product> productList = proRes.findAll();
        if (productList.size() > 0) {
            return productList;
        } else {
            return new ArrayList<Product>();
        }
    }

    //get by id
    public Product getProductById(Integer id) throws RecordNotFoundException {
        Optional<Product> product = proRes.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new RecordNotFoundException("No product record exist for given id", id);
        }
    }

    //update or create
    public Product createOrUpdateProduct(Product entity) throws RecordNotFoundException {
        if (entity.getProductId() != null) {
            Optional<Product> oldPro = proRes.findById(entity.getProductId());

            if (oldPro.isPresent()) {
                Product newEntity = oldPro.get();
                newEntity.setCategoryId(entity.getCategoryId());
                newEntity.setProductName(entity.getProductName());
                newEntity.setProductPrice(entity.getProductPrice());
                newEntity.setPercentSale(entity.getPercentSale());
                newEntity.setPriceSale(entity.getPriceSale());
                newEntity.setAmount(entity.getAmount());
                newEntity.setDescription(entity.getDescription());
                newEntity.setProductImage(MobiShopUtil.getBaseUrl() + entity.getProductImage());
                newEntity.setStatus(entity.getStatus());

                newEntity = proRes.save(newEntity);

                return newEntity;
            } else {
                entity = proRes.save(entity);

                return entity;
            }
        } else {
            entity = proRes.save(entity);
            return entity;
        }
    }

    //delete
    public void deleteProductById(Integer id) throws RecordNotFoundException {
        Optional<Product> student = proRes.findById(id);

        if (student.isPresent()) {
            proRes.deleteById(id);
        } else {
            throw new RecordNotFoundException("No product record exist for given id", id);
        }
    }
}
