package com.daki.db.repository;

import com.daki.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUserEmail(String email);
    Optional<User> findByUserEmail(String email);
    //중복체크
    boolean existsByUserEmail(String email);
    boolean existsByUserNickname(String nickName);

}
