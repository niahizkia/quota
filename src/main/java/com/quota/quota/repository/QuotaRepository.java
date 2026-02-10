package com.quota.quota.repository;

import com.quota.quota.entity.Account;
import com.quota.quota.entity.Quota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuotaRepository extends JpaRepository<Quota, UUID> {

    @Query("""
            SELECT Count(t)
            FROM Transaction AS t
            WHERE t.quota.id = :quotaId 
            AND t.status <> 'canceled'
            """)
    public Integer getTotalReservedQuota(@Param("quotaId") UUID quotaId);
    Optional<Quota> findByQuotaName(String quotaName);
}
