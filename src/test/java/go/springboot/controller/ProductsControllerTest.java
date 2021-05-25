package go.springboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import go.springboot.entity.Product;
import go.springboot.service.ProductService;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

    @Autowired
    private ObjectMapper objectMapper;

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

//    @AfterEach
//    void tearDown() {
//        System.out.println("tearDown todo");
//        //reset(productService);
//    }



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
    void saveProduct() throws Exception {
        // create updated product
        Product updatedProduct = Product.builder()
                .id(randomid)
                .name("updated-product")
                .type("test-save").category("testing")
                .description("test-desc").price(99.99)
                .build();

        given(productService.save(any()))
        .willReturn(updatedProduct);

        System.out.println("updatedProduct="+toJson(updatedProduct));

        mockMvc.perform(post(API_PRODUCTS)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(toJson(updatedProduct)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                ;

    }

    String toJson(Product product)  {
        return "{\"id\": \""+product.getId()+"\", " +
                "\"name\": \""+product.getName()+"\", "+
                "\"type\": \""+product.getType()+"\", "+
                "\"category\": \""+product.getCategory()+"\", "+
                "\"description\": \""+product.getDescription()+"\", "+
                "\"price\": "+product.getPrice()+
                "}";
    }

    @Test
    @Disabled("todo")
    void updateProduct() {
    }

    @Test
    void deleteProduct() throws Exception {
        //given(productService.delete(any())).willReturn void
        mockMvc.perform(delete(API_PRODUCTS+randomid))
                .andExpect(status().isNoContent());
        // verify that productService.delete() was called with any argument
        verify(productService, times(1)).delete(any());
        // verify that productService.delete() was called with id randomid
        verify(productService, times(1)).delete(randomid);
    }
}