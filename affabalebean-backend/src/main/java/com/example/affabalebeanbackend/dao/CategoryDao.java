package com.example.affabalebeanbackend.dao;

import com.example.affabalebeanbackend.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category,Integer> {
}
