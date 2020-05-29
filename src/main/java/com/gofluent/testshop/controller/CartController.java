package com.gofluent.testshop.controller;

import com.gofluent.testshop.datasource.CartRepository;
import com.gofluent.testshop.model.Cart;
import com.gofluent.testshop.model.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cart")
@RestController
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @GetMapping
    public JsonObject getAllItems() {
        List<Cart> cartItems = (List<Cart>) cartRepository.findAll();
        return new JsonObject(200, "GET all cart items", cartItems.size(), cartItems);
    }

    @GetMapping(path = "{token}")
    public JsonObject getAllCartItems(@PathVariable("token") String token) {
        List<Cart> cartItems = cartRepository.findAllCartItems(token);
        return new JsonObject(200, "GET all cart items", cartItems.size(), cartItems);
    }

    @PostMapping
    public JsonObject findByTokenAndBrand(@RequestBody Cart cart) {
        Cart oldCart = cartRepository.findCartByTokenAndBrand(cart.getToken(), cart.getBrand_name());
        String message = "";
        if(oldCart == null) {
            cartRepository.save(cart);
            message = "SAVE new cart item";
        }
        else {
            cartRepository.updateItemCount(cart.getToken(), cart.getBrand_name());
            message = "UPDATE item count";
        }
        return new JsonObject(200, message, 0, cart);
    }

    @DeleteMapping(path = "{token}/{brand_name}")
    public JsonObject deleteCartItem(@PathVariable("token") String token, @PathVariable("brand_name") String brand_name) {
        cartRepository.deleteCartItem(token, brand_name);
        return new JsonObject(200, "DELETE cart item", 0, null);
    }

}
