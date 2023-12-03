package com.baum.canteenApp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    private String categoryId;

    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
