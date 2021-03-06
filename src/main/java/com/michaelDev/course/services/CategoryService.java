package com.michaelDev.course.services;

import com.michaelDev.course.entities.Category;
import com.michaelDev.course.repositories.CategoryRepository;
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
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category insert(Category obj){

        return repository.save(obj);
    }

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long id){
        Optional<Category> obj= repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Category update(Long id, Category obj){
        try {
            Category entity = repository.getOne(id);
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

    private void updateData(Category entity, Category obj){
        entity.setName(obj.getName());
    }
}
