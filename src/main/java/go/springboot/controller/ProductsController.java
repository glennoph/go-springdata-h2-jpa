package go.springboot.controller;

import go.springboot.entity.Product;
import go.springboot.repository.ProductRepository;
import go.springboot.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/products")
public class ProductsController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    // get product
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable(name="id") String id) {
        log.info("id="+id);
        // find product by id, return null if not found
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // save product
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody Product reqProduct) {
        log.info("request product="+reqProduct.toString());
        return productService.save(reqProduct);
    }

    // update product
    @RequestMapping(path = "{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@PathVariable("id")  String id,
                                 @RequestBody Product reqProduct) {
        log.info("id="+id);
        return productService.update(id, reqProduct);
    }

    // delete prduct
    @RequestMapping(path = "{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProduct(@PathVariable("id")  String id) {
        log.info("id="+id);
        productService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
