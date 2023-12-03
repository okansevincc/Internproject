package com.baum.canteenApp.service;


import com.baum.canteenApp.constant.Constant;
import com.baum.canteenApp.converter.CategoryConverter;
import com.baum.canteenApp.exception.CategoryAlreadyExistException;
import com.baum.canteenApp.exception.CategoryNotFoundException;
import com.baum.canteenApp.model.Category;
import com.baum.canteenApp.repository.CategoryRepository;
import com.baum.canteenApp.request.CreateCategoryRequest;
import com.baum.canteenApp.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public CategoryResponse create(CreateCategoryRequest createCategoryRequest){
        Optional<Category> categoryByCategoryName =
                categoryRepository.findCategoryByCategoryName(createCategoryRequest.categoryName());

        if (categoryByCategoryName.isPresent()){
            throw new CategoryAlreadyExistException(Constant.CATEGORY_ALREADY_EXIST);
        }

        Category category = new Category(createCategoryRequest.categoryName());

        return categoryConverter.convert(categoryRepository.save(category));
    }
    public List<CategoryResponse> findAllCategories(){
        return categoryConverter.convert(categoryRepository.findAll());
    }

    protected Category findByCategoryId(String categoryId){
        return categoryRepository.findById(categoryId).orElseThrow
                (
                        () -> new CategoryNotFoundException(Constant.CATEGORY_NOT_FOUND)
                );
    }
}
