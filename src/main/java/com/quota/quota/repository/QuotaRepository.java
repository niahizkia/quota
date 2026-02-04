package com.quota.quota.repository;

import com.quota.quota.entity.Quota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotaRepository extends JpaRepository<Quota, String> {

    @Query("""
            SELECT Count(t)
            FROM Transaction AS t
            WHERE t.quota.quotaId = :quotaId 
            AND t.status <> 'canceled'
            """)
    public Integer getTotalReservedQuota(@Param("quotaId") String quotaId);

}
