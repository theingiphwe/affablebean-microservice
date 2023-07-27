package com.example.affabalebeanbackend.controller;

import com.example.affabalebeanbackend.dao.ProductDao;
import com.example.affabalebeanbackend.entity.Product;
import com.example.affabalebeanbackend.entity.Products;
import com.example.affabalebeanbackend.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @GetMapping("/products")
    public Products listProducts(){
        return productService.listAllProducts();
    }

}
