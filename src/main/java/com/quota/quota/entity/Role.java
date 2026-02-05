package com.quota.quota.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    @Id
    @Column(name = "role_id")
    private String roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "created_at")
    private String createdAt;

    //relationship: One Role can have many Account
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Account> accounts;

    //relationship: One Quota can have many Tran
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Aclm> aclms;
}
