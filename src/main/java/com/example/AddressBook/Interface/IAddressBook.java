package com.example.AddressBook.Interface;

import com.example.AddressBook.Dto.AddressBookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAddressBook {
    List<AddressBookDto> getAllContacts();
    AddressBookDto saveContacts(AddressBookDto contact);
    AddressBookDto getContactById(Long id);
    AddressBookDto updateContact(Long id, AddressBookDto contact);
    String deleteContact(Long id);
}
