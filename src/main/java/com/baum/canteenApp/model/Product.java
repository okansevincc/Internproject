package com.baum.canteenApp.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor

public class Product {

    @Id
    private String productId;

    private String productName;

    private Double price;

    private Integer unitInStock;

    @DBRef
    private Category category;

    public Product(String productName, Double price, Integer unitInStock, Category category) {
        this.productName = productName;
        this.price = price;
        this.unitInStock = unitInStock;
        this.category = category;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public Double getPrice() {
        return this.price;
    }

    public Integer getUnitInStock() {
        return this.unitInStock;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setUnitInStock(Integer unitInStock) {
        this.unitInStock = unitInStock;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Product)) return false;
        final Product other = (Product) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$productId = this.getProductId();
        final Object other$productId = other.getProductId();
        if (this$productId == null ? other$productId != null : !this$productId.equals(other$productId)) return false;
        final Object this$productName = this.getProductName();
        final Object other$productName = other.getProductName();
        if (this$productName == null ? other$productName != null : !this$productName.equals(other$productName))
            return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final Object this$unitInStock = this.getUnitInStock();
        final Object other$unitInStock = other.getUnitInStock();
        if (this$unitInStock == null ? other$unitInStock != null : !this$unitInStock.equals(other$unitInStock))
            return false;
        final Object this$category = this.getCategory();
        final Object other$category = other.getCategory();
        if (this$category == null ? other$category != null : !this$category.equals(other$category)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Product;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $productId = this.getProductId();
        result = result * PRIME + ($productId == null ? 43 : $productId.hashCode());
        final Object $productName = this.getProductName();
        result = result * PRIME + ($productName == null ? 43 : $productName.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $unitInStock = this.getUnitInStock();
        result = result * PRIME + ($unitInStock == null ? 43 : $unitInStock.hashCode());
        final Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        return result;
    }

    public String toString() {
        return "Product(productId=" + this.getProductId() + ", productName=" + this.getProductName() + ", price=" + this.getPrice() + ", unitInStock=" + this.getUnitInStock() + ", category=" + this.getCategory() + ")";
    }
}
