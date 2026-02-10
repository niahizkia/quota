package com.quota.quota.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {

    @Column(name = "role_name", length = 50, unique = true, nullable = false)
    private String roleName;

    private String description;

    @Column(name = "created_at")
    private String createdAt;

    //relationship: One Role can have many Account
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Account> accounts;

    //relationship: One Quota can have many Tran
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Acls> acls;
}
