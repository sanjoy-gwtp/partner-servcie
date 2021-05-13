package com.surjo.admin.controller;

import com.surjo.admin.model.Cart;
import com.surjo.admin.model.WishList;
import com.surjo.admin.service.WishListService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/wishlist",produces = MediaType.APPLICATION_JSON_VALUE)
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveWishList(@Valid @RequestBody WishList wishList) {
        return ResponseEntity.ok(wishListService.createWishList(wishList));
    }

    @GetMapping
    public ResponseEntity getAllWishList() {
        List<WishList> wishLists = wishListService.getAllWishList();
        return ResponseEntity.ok(wishLists);
    }

    @GetMapping(path = "/customer")
    public ResponseEntity getCarts() {
        List<WishList> wishLists = wishListService.getWishListByCustomer();
        return ResponseEntity.ok(wishLists);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getWishListById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(wishListService.getWishListById(id));
    }

    @DeleteMapping
    public ResponseEntity deleteWishList(@RequestParam(name="id",required = true)Long id) {
        wishListService.deleteWishList(id);
        return ResponseEntity.accepted().build();
    }
}
