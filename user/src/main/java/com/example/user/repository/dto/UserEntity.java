package com.example.user.repository.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_profile")
public class UserEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID uid;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
