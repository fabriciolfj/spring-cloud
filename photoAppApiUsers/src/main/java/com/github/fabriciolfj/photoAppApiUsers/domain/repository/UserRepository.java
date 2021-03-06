package com.github.fabriciolfj.photoAppApiUsers.domain.repository;

import com.github.fabriciolfj.photoAppApiUsers.domain.entity.UserEntity;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(final String email);
    Optional<UserEntity> findByUserId(final String userId);
}
