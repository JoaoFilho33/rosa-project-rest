package com.rosa.rosaRest.controllers;

import com.rosa.rosaRest.Enum.StatusEnum;
import com.rosa.rosaRest.dtos.ProductRecordDto;
import com.rosa.rosaRest.models.ProductModel;
import com.rosa.rosaRest.repositories.ProductRepository;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
//@RequestMapping(value = "/open-api", produces = {"application/json"})
//@Tag(name = "open-api")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        ProductModel savedProduct = productRepository.save(productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(
            @RequestParam(name = "status", required = false) StatusEnum status,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "sort", required = false, defaultValue = "name") String sort) {

        List<ProductModel> products;

        if (status != null) {
            products = productRepository.findByStatus(status);
        } else if (search != null) {
            products = productRepository.findByNameContainingIgnoreCase(search);//filtrada por nome
        } else {
            products = productRepository.findAll();
        }

        // Ordenação
        if ("name".equalsIgnoreCase(sort)) {
            //products.sort(Comparator.comparing(ProductModel::getName));
            products.sort(Comparator.comparing(p -> p.getName().toLowerCase()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }


//    @GetMapping("/products")
//    public ResponseEntity<List<ProductModel>> getAllProducts() {
//        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
//    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> product0 = productRepository.findById(id);
        if(product0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product0.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductRecordDto productRecordDto) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID inválido");
        }

        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        var productModel = productOptional.get();
        BeanUtils.copyProperties(productRecordDto, productModel);

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }
    @PutMapping("/products/{id}/status")
    public ResponseEntity<Object> updateProductStatus(@PathVariable(value = "id") UUID id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID inválido");
        }

        Optional<ProductModel> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        ProductModel product = optionalProduct.get();
        product.setStatus(product.getStatus() == StatusEnum.AVAILABLE ? StatusEnum.UNAVAILABLE : StatusEnum.AVAILABLE);

        productRepository.save(product);

        return ResponseEntity.status(HttpStatus.OK).body("Product status updated successfully");
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID inválido");
        }

        Optional<ProductModel> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        productRepository.delete(productOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }


}