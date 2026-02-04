package com.quota.quota.repository;

import com.quota.quota.dto.TransactionDetailDTO;
import com.quota.quota.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query("""
            SELECT new com.quota.quota.dto.TransactionDetailDTO(t.tranId, t.quotaId, t.status, t.takenBy, t.createdAt)
            FROM Transaction t
            WHERE t.quotaId = :quotaId
            """)
    public List<TransactionDetailDTO> getTranByQuotaId(@Param("quotaId") String quotaId);
}
