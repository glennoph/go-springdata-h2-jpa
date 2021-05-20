# go-springdata-h2-jpa

## h2 
[console-url](http://localhost:8080/h2)
* NB update JDBC URL in order to connect to db, see below
  `jdbc:h2:file:./database/Products`

## /api/products
* get: get product by id
* post: create product
* put: update product
* delete: delete product

## api-docs
http://localhost:8080/api-docs/

## swagger
http://localhost:8080/swagger-ui.html

## actuator

### dependencies
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-hateoas</artifactId>
		</dependency>
```

### actuator health
http://localhost:8080/actuator/health

* returns {"status":"UP"}