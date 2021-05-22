package com.michaelDev.course.resources;

import com.michaelDev.course.entities.Order;
import com.michaelDev.course.entities.User;
import com.michaelDev.course.services.OrderService;
import com.michaelDev.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
    @Autowired
    private OrderService service;
    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order obj){
        obj = service.insert(obj);
        // java.net.uri import
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        List<Order> list = service.findAll();

        return ResponseEntity.ok(list);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        Order obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order obj ){
        obj = service.update(id, obj);

        return ResponseEntity.ok().body(obj);
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
