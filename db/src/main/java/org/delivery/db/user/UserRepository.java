package org.delivery.db.user;

import org.delivery.db.user.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> { // JpaRepository<어떠한 Entity쓸건지, id라는 변수의 타입>

    // select * from user where id = ? and status = ? order by id desc limit 1
    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);

    // select * from user where email = ? and password = ? and status = ? order by id desc limit 1
    Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus status);

}
