package com.example.AddressBook.Repository;

import com.example.AddressBook.Entity.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUserEntity, Long> {
    public AuthUserEntity findByEmail(String email);
    public AuthUserEntity findByToken(String token);
}
