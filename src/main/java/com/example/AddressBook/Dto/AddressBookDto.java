package com.example.AddressBook.Dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AddressBookDto {
    private Long id;
    private String name;
    private String phone;

    public AddressBookDto(Long id, String phone, String name) {
        this.id = id;
        this.phone = phone;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
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
}
