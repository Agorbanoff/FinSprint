package org.example.persistence.repository;

import org.example.persistence.model.JwtEntity;
import org.example.persistence.model.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtRepository extends JpaRepository<JwtEntity, Integer> {
    void deleteByUserAccount_Id(Integer integer);
    void deleteRefreshTokenByUserAccount(UserAccountEntity userAccountEntity);
    Optional<JwtEntity> findByUserAccount(UserAccountEntity userAccountEntity);
}
