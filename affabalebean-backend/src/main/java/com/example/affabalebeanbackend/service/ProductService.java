package com.example.affabalebeanbackend.service;

import com.example.affabalebeanbackend.dao.CategoryDao;
import com.example.affabalebeanbackend.dao.ProductDao;
import com.example.affabalebeanbackend.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    public Products listAllProducts(){
        return new Products(productDao.findAll().spliterator());
    }
}
