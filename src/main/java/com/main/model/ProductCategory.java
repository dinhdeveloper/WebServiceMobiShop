package com.main.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "ProductCategory")
public class ProductCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CategoryId")
    private Integer categoryId;
    @Column(name = "CategoryName")
    private String categoryName;
    @Column(name = "CategoryImage")
    private String categoryImage;
    @Column(name = "PromotionId")
    private Integer promotionId;

    public ProductCategory() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }
}
