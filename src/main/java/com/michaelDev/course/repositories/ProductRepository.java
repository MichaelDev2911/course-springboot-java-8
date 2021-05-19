package com.michaelDev.course.repositories;

import com.michaelDev.course.entities.Category;
import com.michaelDev.course.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
