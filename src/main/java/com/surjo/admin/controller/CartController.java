package com.surjo.admin.controller;

import com.surjo.admin.model.Cart;
import com.surjo.admin.model.Product;
import com.surjo.admin.service.CartService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cart",produces = MediaType.APPLICATION_JSON_VALUE)
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCart(@Valid @RequestBody Cart cart) {
        return ResponseEntity.ok(cartService.createCart(cart));
    }

    @GetMapping
    public ResponseEntity getAllCart() {
        List<Cart> cartList = cartService.getAllCart();
        return ResponseEntity.ok(cartList);
    }

    @GetMapping(path = "/customer")
    public ResponseEntity getCarts() {
        List<Cart> cartList = cartService.getCartByCustomer();
        return ResponseEntity.ok(cartList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getCartById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(cartService.getCartById(id));
    }

    @DeleteMapping
    public ResponseEntity deleteCart(@RequestParam(name="id",required = true)Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.accepted().build();
    }
}
