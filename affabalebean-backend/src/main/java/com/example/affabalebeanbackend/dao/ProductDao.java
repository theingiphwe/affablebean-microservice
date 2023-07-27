package com.example.affabalebeanbackend.dao;

import com.example.affabalebeanbackend.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product,Integer> {
}
