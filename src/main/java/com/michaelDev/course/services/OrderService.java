package com.michaelDev.course.services;

import com.michaelDev.course.entities.Order;
import com.michaelDev.course.entities.User;
import com.michaelDev.course.repositories.OrderRepository;
import com.michaelDev.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> obj= repository.findById(id);
        return obj.get();
    }
}
