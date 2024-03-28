package com.itpm.fashionretailapi.repository;

import com.itpm.fashionretailapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
}
