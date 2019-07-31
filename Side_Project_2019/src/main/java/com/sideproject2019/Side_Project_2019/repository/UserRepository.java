package com.sideproject2019.Side_Project_2019.repository;

import com.sideproject2019.Side_Project_2019.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
}
