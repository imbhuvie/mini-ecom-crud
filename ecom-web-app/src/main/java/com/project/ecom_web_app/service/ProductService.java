package com.project.ecom_web_app.service;

import com.project.ecom_web_app.model.Product;
import com.project.ecom_web_app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProduct(){
       return productRepository.findAll();
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageDate(imageFile.getBytes());
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageDate(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());

        return productRepository.save(product);
    }

    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword);
    }
}
