package com.example.AddressBook.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@Table(name = "AddressBook")
public class AddressBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Give A Name")
    private String name;

    @NotBlank(message = "Give a Phone Number")
    private String phone;

    public String getPhone() {
        return phone;
    }

    public AddressBookEntity(Long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
