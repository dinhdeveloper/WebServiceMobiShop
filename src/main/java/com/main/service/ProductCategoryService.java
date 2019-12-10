package com.main.service;

import com.main.MobiShopUtil;
import com.main.exception.RecordNotFoundException;
import com.main.model.ProductCategory;
import com.main.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository repository;

    public List<ProductCategory> getAllProCate() {
        List<ProductCategory> productCategories = repository.findAll();
        if (productCategories.size() > 0) {
            return productCategories;
        } else {
            return new ArrayList<ProductCategory>();
        }
    }

    public ProductCategory findCateById(Integer id) throws RecordNotFoundException {
        Optional<ProductCategory> productCategory = repository.findById(id);
        if (productCategory.isPresent()) {
            return productCategory.get();
        } else {
            throw new RecordNotFoundException("No category record exist for given id", id);
        }
    }

    //update or create
    public ProductCategory createOrUpdateProduct(ProductCategory entity) throws RecordNotFoundException {
        if (entity.getCategoryId() != null) {
            Optional<ProductCategory> oldCategory = repository.findById(entity.getCategoryId());

            if (oldCategory.isPresent()) {
                ProductCategory newEntity = oldCategory.get();
                newEntity.setCategoryId(entity.getCategoryId());
                newEntity.setCategoryName(entity.getCategoryName());
                newEntity.setCategoryImage(MobiShopUtil.getBaseUrl() + entity.getCategoryImage());
                newEntity.setPromotionId(entity.getPromotionId());

                newEntity = repository.save(newEntity);
                return newEntity;
            } else {
                entity = repository.save(entity);

                return entity;
            }
        } else {
            entity = repository.save(entity);
            return entity;
        }
    }

    //delete
    public void deleteCategoryById(Integer id) throws RecordNotFoundException {
        Optional<ProductCategory> productCategory = repository.findById(id);

        if (productCategory.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No category record exist for given id", id);
        }
    }
}
