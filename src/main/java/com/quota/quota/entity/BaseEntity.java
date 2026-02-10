package com.quota.quota.entity;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity implements Serializable { // remove Serializable if you don;t have any plan using Caching or Session Storage
    // Manually setting this prevents "InvalidClassException" when you
    // update your code but still have old data in your Redis cache.
    private static final long serialVersionUID = 1L;
    @Id
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @PrePersist
    public void ensureId() {
        if (id == null) {
            this.id = Generators.timeBasedEpochGenerator().generate();
        }
    }
}
