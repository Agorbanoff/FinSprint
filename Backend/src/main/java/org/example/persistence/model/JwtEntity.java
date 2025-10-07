package org.example.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tokens")
@Data
public class JwtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String refreshToken;

    @Column
    private String iat;

    @Column
    private String exp;

    @Column
    private String jti;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private UserAccountEntity userAccount;
}