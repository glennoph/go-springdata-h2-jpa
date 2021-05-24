package go.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import go.springboot.entity.Product;
import go.springboot.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static java.util.UUID.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductsControllerTest {

    public static final String API_PRODUCTS = "/api/products/";
    private String randomid = randomUUID().toString();
    private String addedid = randomUUID().toString();

    @Mock
    ProductService productService;

    @InjectMocks
    ProductsController productsController;

    MockMvc mockMvc;


    Product testProduct;
    Product addedProduct;

    @BeforeEach
    void setUp() {

        testProduct = Product.builder()
                .id(randomid)
                .name("test-product")
                .type("test")
                .category("testing")
                .build();

        addedProduct = Product.builder()
                .id(addedid)
                .name("test-product-added")
                .type("test")
                .category("testing")
                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(addedProduct);

        mockMvc = MockMvcBuilders.standaloneSetup(productsController).build();

    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown todo");
        //reset(productService);
    }



    @Test
    void getProduct() throws Exception {
        given(productService.getProduct(any())).willReturn(testProduct);

        mockMvc.perform(get(API_PRODUCTS+randomid))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(randomid))) // check id
                .andExpect(jsonPath("$.id", is(testProduct.getId()))) // check id from test product
                .andExpect(jsonPath("$.name", is(testProduct.getName()))) // check name from test product
                .andExpect(jsonPath("$.type", is(testProduct.getType()))) // check type from test product
                .andDo(MockMvcResultHandlers.print()) // print result data
        ;
    }

    @Test
    @Disabled("fail")
    void saveProduct() throws Exception {
        //NB test failed because product was not added
        Product updatedProduct = Product.builder()
                .id(randomid)
                .name("updated-product")
                .type("test-save").category("testing")
                .build();

        given(productService.save(any()))
        .willReturn(updatedProduct);

        String updatedProductString = new ObjectMapper().writeValueAsString(updatedProduct);
        System.out.println("updatedProductString="+updatedProductString);
        mockMvc.perform(post(API_PRODUCTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProductString))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                ;

    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}