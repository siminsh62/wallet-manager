# WALLET MICROSERVICE

## Technology used
```
- Java 8+ 
- Spring Boot 
- Hibernate & JPA
- H2
- Docker 
- Maven 
- Junit
```
## How to run

git
```
git clone https://github.com/siminsh62/shoppingcart
```
Build
```
mvn clean install 
```
```
spring boot run 
```
Travis CI

The Travis CI environment contains various versions of OpenJDK, Gradle, Maven and Ant.
To use the Java environment, add the following to your.travis.yml in root
```
mvn install -DskipTests=true -Dmaven.javadoc.skip=true -B -V
mvn test -B
```

- You can use postman (https://www.postman.com/) tool to call endpoints
- Swagger documentation    http://localhost:8080/api-docs
## List of endpoints:
```
Swagger documentation                                  http://localhost:8080/api-docs

Create new product                          POST        http://localhost:8080/product
Get product by Id                           GET         http://localhost:8080/product/id
Get All product                             GET         http://localhost:8080/product
Delete produc:                              DELETE      http://localhost:8080/product/id

Create new ShoppingCart                     POST        http://localhost:8080/api
Get ShoppingCart by Id                      GET         http://localhost:8080/api/cartId
Get all ShoppingCart                        GET         http://localhost:8080/api
Add product to ShoppingCart                 PUT         http://localhost:8080/api/cartId/product
delete Product fromShoppingCart             DELETE      http://localhost:8080/api/cartId/product/productId
Delete ShoppingCart                         DELETE      http://localhost:8080/api/cartId
```
## Data models

ShoppingCart:

```
     Long id;
     String countryCode
     String currency
     List<Product> products
     String created
     String updated

```
Product:

```
     Long id;
     String productId;
     String description;
     String category;
     BigDecimal price;
     String created;
     String updated;

```
## Test case

1. Create products: http://localhost:8080/product

```json
      {
           "productId":"1135621-5605-4d75-8317-db282c498c7f",
           "description":"Apple iphone 12",
           "category":"ELECTRONICS",
           "price":7325.05,
           "created":"2021-05-07T20:30:00",
           "updated":"2021-08-07T20:30:00"
      }
   

```
2. Create Shopping cart: http://localhost:8080/api

```json
     {
        "countryCode":"US",
        "currency":"USD",
        "created":"2021-05-07T20:30:00",
        "updated":"2021-08-07T20:30:00"
     }

```
3. Add product or products to cart Shopping: http://localhost:8080/api/1/product

```json
     {
       "id": 1,
       "countryCode": "US",
       "currency": "USD",
       "products":
                  [
                     {
                      "id": 1,
                      "productId": "1135621-5605-4d75-8317-db282c498c7f",
                      "description": "Apple iphone 12",
                      "category": "ELECTRONICS",
                      "price": 7325.05,
                      "created": "2021-05-07T20:30:00",
                      "updated": "2021-08-07T20:30:00"
                    }
                 ]          ,
      "created": "2021-05-07T20:30:00",
      "updated": "2021-08-07T20:30:00"
    }
```
4.Get product by Id from shopping cart: http://localhost:8080/api/1/product/1
```json
          {
            "id": 1,
            "productId": "1135621-5605-4d75-8317-db282c498c7f",
            "description": "Apple iphone 12",
            "category": "ELECTRONICS",
            "price": 7325.05,
            "created": "2021-05-07T20:30:00",
            "updated": "2021-08-07T20:30:00"
          }
        
```
5.Delete product from shopping cart: http://localhost:8080/api/1/product/1
```json
          {
            "id": 1,
            "countryCode": "US",
            "currency": "USD",
            "products": [],
            "created": "2021-05-07T20:30:00",
            "updated": "2021-08-07T20:30:00"
         }
        
```
Test coverage

-![ ](images/coverage2.jpg)
-![ ](images/coverage1.jpg)

