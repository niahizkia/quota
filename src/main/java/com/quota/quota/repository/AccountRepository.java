package com.quota.quota.repository;

import com.quota.quota.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("""
            SELECT COUNT(acc.username)
            FROM Account AS acc
            WHERE acc.username = :username
            """)
    public Long countExistingUser(@Param("username") String username);
}
