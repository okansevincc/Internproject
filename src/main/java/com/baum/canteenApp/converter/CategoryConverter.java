package com.baum.canteenApp.converter;

import com.baum.canteenApp.model.Category;
import com.baum.canteenApp.response.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryConverter {

    public CategoryResponse convert(Category from){
        return new CategoryResponse(from.getCategoryId(), from.getCategoryName());
    }

    public List<CategoryResponse> convert(List<Category> fromList){
        return fromList.stream()
                .map(category -> new CategoryResponse(category.getCategoryId(), category.getCategoryName()))
                .toList();
    }
}
