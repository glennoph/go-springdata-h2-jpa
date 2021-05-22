package go.springboot.controller;

import go.springboot.entity.Product;
import go.springboot.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import static java.util.UUID.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductsControllerTest {

    private String randomid = randomUUID().toString();
    @Mock
    ProductService productService;

    @InjectMocks
    ProductsController productsController;

    MockMvc mockMvc;

    Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = Product.builder()
                .id(randomid)
                .name("test-product")
                .type("test")
                .category("testing")
                .build();

        mockMvc = MockMvcBuilders.standaloneSetup(productsController)
                .build();
    }



    @Test
    void getProduct() throws Exception {
        given(productService.getProduct(any())).willReturn(testProduct);

        mockMvc.perform(get("/api/products/"+randomid))
        .andExpect(status().isOk());
    }

    @Test
    void saveProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}