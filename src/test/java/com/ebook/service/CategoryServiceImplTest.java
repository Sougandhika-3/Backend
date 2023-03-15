package com.ebook.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ebook.dto.CategoryDto;
import com.ebook.entites.Category;
import com.ebook.exception.ResourceNotFoundException;
import com.ebook.mapper.CategoryMapper;
import com.ebook.repository.CategoryRepository;

public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCategoryById() {
        Category category = new Category();
        category.setId(1);
        category.setCategoryName("Test Category");
        category.setDescription("This is a test category");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1);
        categoryDto.setCategoryName("Test Category");
        categoryDto.setDescription("This is a test category");

        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        Mockito.when(categoryMapper.categoryToDto(category)).thenReturn(categoryDto);

        CategoryDto result = categoryService.getCategoryById(1);

        assertEquals(categoryDto.getId(), result.getId());
        assertEquals(categoryDto.getCategoryName(), result.getCategoryName());
        assertEquals(categoryDto.getDescription(), result.getDescription());
    }

    @Test
    public void testGetCategoryByIdNotFound() {
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategoryById(1));
    }

}

