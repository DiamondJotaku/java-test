package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> search(String query) {
        return productRepository
            .findByNameContainingIgnoreCaseOrCategory_NameContainingIgnoreCase(query, query);
    }

    public Product create(Product product) { return productRepository.save(product); }
    public Product update(Long id, Product updated) {
        updated.setId(id);
        return productRepository.save(updated);
    }
    public void delete(Long id) { productRepository.deleteById(id); }
}
