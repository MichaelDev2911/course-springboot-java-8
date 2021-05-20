package com.michaelDev.course.repositories;

import com.michaelDev.course.entities.OrderItem;
import com.michaelDev.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
