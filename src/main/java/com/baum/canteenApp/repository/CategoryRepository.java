package com.baum.canteenApp.repository;

import com.baum.canteenApp.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category,String> {

    Optional<Category> findCategoryByCategoryName(String categoryName);
}
