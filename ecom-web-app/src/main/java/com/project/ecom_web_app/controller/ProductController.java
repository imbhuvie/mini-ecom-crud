package com.project.ecom_web_app.controller;

import com.project.ecom_web_app.model.Product;
import com.project.ecom_web_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin  //to cross origin resources management if client and server has different ports.
public class ProductController {


    //to create object
    @Autowired
    ProductService productService;

    @RequestMapping("/")
    public String greet(){
        return "Hi, I am Bhupendra Verma. A Java FullStack Developer using Spring Boot and React.";
    }
//  To get all products from DB using GET method request.
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }
//  To get product by ID from DB
    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId){
        Product product=productService.getProductById(productId);
        if(product!=null)
            return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//  To add Product to the Database using POST method.
    @PostMapping ("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){
//        System.out.println(product +" "+ imageFile);
        try{
            System.out.println(product );
            Product product1 = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }  catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
//  To get image by ID of the product from the DB.
    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageById(@PathVariable int productId){
        Product product=productService.getProductById(productId);
        byte[] imageFile=product.getImageDate();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

//    TO update the product details in DB
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,
                                          @RequestPart Product product,
                                          @RequestPart MultipartFile imageFile){
        Product product1=null;
        try {
            product1=productService.updateProduct(id,product,imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        }
        if(product1!=null){
            return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
        }else
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
    }
    // TO DELETE the product of given ID from DB.
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable int id){
        Product product=productService.getProductById(id);
        if(product!=null){
            productService.deleteProductById(id);
            return new ResponseEntity<>("Product Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
    }

//    TO search the products by keywords from DB
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        List<Product> productList = productService.searchProduct(keyword);
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }

}
