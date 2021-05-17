package com.michaelDev.course.repositories;

import com.michaelDev.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {

}
