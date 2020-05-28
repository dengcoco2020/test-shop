package com.gofluent.testshop.datasource;

import com.gofluent.testshop.model.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Integer> {

    @Query(value = "SELECT id, base_price, brand_name, description, prod_id, prod_name, token, item_count, base_price * item_count as total_amount FROM cart WHERE token = :token", nativeQuery = true)
    List<Cart> findAllCartItems(@Param("token") String token);

    @Query(value = "SELECT * FROM cart WHERE token = :token AND brand_name = :brand_name", nativeQuery = true)
    Cart findCartByTokenAndBrand(@Param("token") String token, @Param("brand_name") String brand_name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cart set item_count = item_count + 1, total_amount = item_count * base_price WHERE token = :token AND brand_name = :brand_name", nativeQuery = true)
    public void updateItemCount(@Param("token") String token, @Param("brand_name") String brand_name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cart WHERE token = :token AND brand_name = :brand_name", nativeQuery = true)
    public void deleteCartItem(@Param("token") String token, @Param("brand_name") String brand_name);

}
