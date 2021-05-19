package com.michaelDev.course.repositories;

import com.michaelDev.course.entities.Category;
import com.michaelDev.course.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
