package com.michaelDev.course.services;

import com.michaelDev.course.entities.Category;
import com.michaelDev.course.entities.Product;
import com.michaelDev.course.repositories.ProductRepository;
import com.michaelDev.course.services.exceptions.DatabaseException;
import com.michaelDev.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product insert(Product obj){
        return repository.save(obj);
    }

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> obj= repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    public Product update(Long id, Product obj){
        try {
            Product entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw  new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Product entity, Product obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setPrice(obj.getPrice());

    }

}
