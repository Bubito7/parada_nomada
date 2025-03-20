package com.paradanomada.repository;

import com.paradanomada.entity.User;
import com.paradanomada.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findFirstByEmail(String email);

    User findByUserRole(UserRole userRole);
}
