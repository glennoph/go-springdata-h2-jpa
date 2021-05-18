package go.springboot.service;

import go.springboot.entity.Product;
import go.springboot.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        log.info("setProductRepository");
        this.productRepository = productRepository;
    }

    // get product from id
    public Product getProduct(String id) {
        log.info("id="+id);

        // force exception
        //        log.debug("id null");
        //        id=null;

        // find product by id, return null if not found
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) log.info("product="+product.toString());
        else log.info("product==null");
        return product;
    }

    // save product
    public Product save(Product product) {
        if (product != null) {
            log.info("product=" + product.toString());
        } else {
            log.info("product=null");
        }
        return productRepository.save(product);
    }

    // update product
    public Product update(String id, Product reqProduct) {
        log.info("id="+id);
        Product product = getProduct(id);

        if (product != null) {
            log.info("product=" + product.toString());
            product.setCategory(reqProduct.getCategory());
            product.setDescription(reqProduct.getDescription());
            product.setPrice(reqProduct.getPrice());
            product.setName(reqProduct.getName());
            product.setType(reqProduct.getType());
            return productRepository.save(product);
        } else {
            log.info("product=null not found");
            return null;
        }
    }

    // delete product by id
    public void delete(String id) {
        log.info("id="+id);
        Product product = productRepository.findById(id).orElse(null);
        if (product != null)
            productRepository.delete(product);
    }

}
