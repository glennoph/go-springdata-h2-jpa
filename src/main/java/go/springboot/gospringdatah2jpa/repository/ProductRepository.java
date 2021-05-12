package go.springboot.gospringdatah2jpa.repository;

import go.springboot.gospringdatah2jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    // find by type
    List<Product> findByType(String type);
    // find all products by description
    List<Product> findByDescription(String description);
    // find all products by name list
    List<Product> findByNameIn(List<String> nameList);
    // find all products by name
    List<Product> findByName(String name);
}
