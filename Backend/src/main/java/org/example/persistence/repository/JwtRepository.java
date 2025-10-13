package org.example.persistence.repository;

import org.example.persistence.model.JwtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtRepository extends JpaRepository<JwtEntity, Integer> {
    void deleteRefreshTokenById(Integer integer);
}
