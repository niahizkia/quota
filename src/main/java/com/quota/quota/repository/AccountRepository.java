package com.quota.quota.repository;

import com.quota.quota.entity.Account;
import com.quota.quota.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query("""
            SELECT COUNT(acc.username)
            FROM Account AS acc
            WHERE acc.username = :username
            """)
    public Long countExistingUser(@Param("username") String username);

    Optional<Account> findByUsername(String username);
}
