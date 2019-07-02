package com.njnu.mapper;

import com.njnu.bean.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> selectAll();
    Category selectById(Integer id);
    int update(Category category);
    int deleteById(Integer id);
    int save(Category category);
}
