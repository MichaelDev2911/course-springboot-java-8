package com.michaelDev.course.services;

import com.michaelDev.course.entities.User;
import com.michaelDev.course.repositories.UserRepository;
import com.michaelDev.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User insert(User obj){
        return repository.save(obj);
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> obj= repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User update(Long id, User obj){
        User entity = repository.getOne(id);
        updateData(entity,obj);
        return repository.save(entity);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private void updateData(User entity, User obj){
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
