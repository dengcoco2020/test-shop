package com.gofluent.testshop.datasource;

import com.gofluent.testshop.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(value = "SELECT p.prod_id, p.base_price, p.description, p.flag, p.prod_name, p.brand_name FROM product p order by p.prod_id", nativeQuery = true)
    List<Product> findAllProducts();
}
