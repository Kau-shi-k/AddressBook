package com.example.AddressBook.Entity;

import com.example.AddressBook.Dto.AuthUserDto;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class AuthUserEntity {
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    @Setter
    private String token;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public AuthUserEntity(String token, String mail, String password, String lastName, String firstName) {
        this.token = token;
        this.mail = mail;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = null;
    }

    public AuthUserEntity(AuthUserDto userDto) {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }
}
