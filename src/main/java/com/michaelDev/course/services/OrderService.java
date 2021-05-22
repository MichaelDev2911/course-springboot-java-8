package com.michaelDev.course.services;

import com.michaelDev.course.entities.Order;
import com.michaelDev.course.entities.User;
import com.michaelDev.course.entities.enums.OrderStatus;
import com.michaelDev.course.repositories.OrderRepository;
import com.michaelDev.course.repositories.UserRepository;
import com.michaelDev.course.services.exceptions.DatabaseException;
import com.michaelDev.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public Order insert(Order obj){
        return repository.save(obj);
    }

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> obj= repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    public Order update(Long id, Order obj){
        try {
            Order entity = repository.getOne(id);
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

    private void updateData(Order entity, Order obj){

        entity.setMoment(obj.getMoment());
        entity.setOrderStatus(obj.getOrderStatus());
        entity.setClient(obj.getClient());
    }
}
