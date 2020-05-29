package com.gofluent.testshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gofluent.testshop.datasource.CartRepository;
import com.gofluent.testshop.model.Cart;
import com.gofluent.testshop.model.JsonObject;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {

    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private CartController cartController;

    @Mock
    private CartRepository cartRepository;

    @MockBean
    private Cart mockCart;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(cartController)
                .build();
    }

    @Test
    public void testGetAllItems() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cart")
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(jsonPath("$.status", Matchers.is(200)));
    }

    @Test
    public void testGetAllCartItems() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cart/token-string")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(jsonPath("$.status", Matchers.is(200)));
    }

    @Test
    public void testDeleteCartItem() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/cart/any-string/any-string"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is(200)));
//                .andExpect(jsonPath("$.message", Matchers.is("DELETE cart item")));
    }

    @Test
    public void testFindByTokenAndBrand() throws Exception {

        mockCart = new Cart();
        mockCart.setBase_price(100);
        mockCart.setBrand_name("Test");
        mockCart.setDescription("Test");
        mockCart.setProd_id(1);
        mockCart.setProd_name("Test");
        mockCart.setToken("Test");
        mockCart.setItem_count(1);
        mockCart.setTotal_amount(100);

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(
                post("/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockCart))
        )
        .andExpect(status().isOk());
    }

}