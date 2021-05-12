package go.springboot.gospringdatah2jpa;

import go.springboot.gospringdatah2jpa.entity.Product;
import go.springboot.gospringdatah2jpa.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

	private ProductRepository productRepository;

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product p1 = new Product();
		p1.setName("testing product");
		p1.setCategory("test");
		p1.setDescription("this is a tesing product");
		p1.setType("a");
		p1.setPrice(0.0);
		productRepository.save(p1);
		log.info("Startup: save product "+ p1.toString());

		Product p2 = new Product();
		p2.setName("testing product 2");
		p2.setCategory("test");
		p2.setDescription("this is a tesing product");
		p2.setType("b");
		p2.setPrice(0.01);
		productRepository.save(p2);
		log.info("Startup: save product "+ p2.toString());


		Product p3 = new Product();
		p3.setName("testing product 3");
		p3.setCategory("test");
		p3.setDescription("this is a tesing product");
		p3.setType("b");
		p3.setPrice(0.01);
		productRepository.save(p3);
		log.info("Startup: save product "+ p3.toString());

		log.info("Startup: find all products");
		List<Product> productList = productRepository.findAll();
		for(Product p : productList) {
			log.info(p.toString());
		}

		log.info("startup: find by type");
		List<Product> pFindByType = productRepository.findByType("b");
		for(Product p : pFindByType) {
			log.info("startup: find by type product=" + p.toString());
		}

		log.info("startup: find by description");
		List<Product> pfindByDesc = productRepository.findByDescription("this is a tesing product");
		for(Product p: pfindByDesc) {
			log.info("startup p="+p.toString());
		}

		log.info("startup: find by name list");
		List<String> nameList = new ArrayList<>();
		nameList.add("testing product 2");
		nameList.add("testing product 3");
		List<Product> pfindByNameList = productRepository.findByNameIn(nameList);
		log.info("startup: find by name list=");

		for(Product p: pfindByNameList) {
			log.info("startup p="+p.toString());
			p.setName(p.getName()+"-updated");
			log.info("startup: update product in db");
			productRepository.save(p);
		}

		// find and delete product 3
		log.info("startup: find by name");
		log.info("startup: product repo count before="+productRepository.count());
		String name3 = "testing product 3-updated";
		List<Product> pfindName3 = productRepository.findByName(name3);

		for(Product p: pfindName3) {
			log.info("startup pname3="+p.toString());
			log.info("startup: delete product in db");
			productRepository.delete(p);
		}
		log.info("startup: product repo count after="+productRepository.count());

	}
}
