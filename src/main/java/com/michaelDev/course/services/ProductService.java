package com.michaelDev.course.services;

import com.michaelDev.course.entities.Category;
import com.michaelDev.course.entities.Product;
import com.michaelDev.course.repositories.ProductRepository;
import com.michaelDev.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> obj= repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
