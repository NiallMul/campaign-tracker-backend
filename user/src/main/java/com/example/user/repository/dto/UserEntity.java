package com.example.user.repository.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
}
