package com.example.affablebeanui.service;

import com.example.affablebeanui.entity.Product;
import com.example.affablebeanui.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public static final int DELIVERY_CHARGE = 3;
    private final CartService cartService;
    private List<Product> products;

    record TransferData(String to_email,String from_email,double amount){}
    private RestTemplate restTemplate=new RestTemplate();


    public ResponseEntity saveCartItem(){
        return restTemplate.getForEntity("http://localhost:9000/transport/cart/save",String.class);
    }

    public ResponseEntity transfer(String to_email,String from_email,double amount){
        var data=new TransferData(to_email,from_email,amount+ DELIVERY_CHARGE);
        return restTemplate.postForEntity("http://localhost:8095/account/transfer",data,String.class);

    }
    public ProductService (final CartService cartService){
        this.cartService=cartService;
        var productResponseEntity=restTemplate.getForEntity("http://localhost:8090/backend/products",Products.class);
        if(productResponseEntity.getStatusCode().is2xxSuccessful()){
            products=productResponseEntity.getBody().getProducts();
            return;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public List<Product> showAllProducts(){
        return products;
    }

    public List<Product> findProductByCategory(int categoryId){
        return products.stream().filter(p->p.getCategory().getId() ==categoryId).collect(Collectors.toList());
    }
    private Product findProduct(int id){
        return products.stream().filter(p->p.getId()==id).findAny().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Product purchaseProduct(int id){
        Product product=findProduct(id);
        cartService.addToCart(product);
        return product;
    }



}
