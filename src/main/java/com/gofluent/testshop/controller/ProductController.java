package com.gofluent.testshop.controller;

import com.gofluent.testshop.datasource.ProductRepository;
import com.gofluent.testshop.model.JsonObject;
import com.gofluent.testshop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public JsonObject getAllProducts() {
        List<Product> products = productRepository.findAllProducts();
        JsonObject jsonObject = new JsonObject(200, "GET all products", products.size(), products);
        return jsonObject;
    }

    @GetMapping(path = "{id}")
    public JsonObject getProductById(@PathVariable("id") Integer id) {
        Product product = productRepository.findById(id).get();
        JsonObject jsonObject = new JsonObject(200, "GET product by ID", 0, product);
        return jsonObject;
    }


}
