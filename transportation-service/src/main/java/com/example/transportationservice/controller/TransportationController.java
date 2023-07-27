package com.example.transportationservice.controller;

import com.example.transportationservice.dao.CartItemDao;
import com.example.transportationservice.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transport")
@RequiredArgsConstructor
public class TransportationController {

    private final CartItemDao cartItemDao;
    private final CartItemService cartItemService;
    @GetMapping("/cart/save")
    public ResponseEntity<?> saveCartItems(){
        cartItemService.getAllCartItems().subscribe(data->{data.forEach(cartItemDao::save);
        });
        return ResponseEntity.status(HttpStatusCode.valueOf(201))
                .body("successfully created");
    }
}
