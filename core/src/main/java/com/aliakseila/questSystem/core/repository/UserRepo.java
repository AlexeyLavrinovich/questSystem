package com.aliakseila.questSystem.core.repository;

import com.aliakseila.questSystem.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String name);

}
