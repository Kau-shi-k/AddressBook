package com.example.AddressBook.Service;

import com.example.AddressBook.Dto.AddressBookDto;
import com.example.AddressBook.Entity.AddressBookEntity;
import com.example.AddressBook.Interface.IAddressBook;
import com.example.AddressBook.Repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressBookService implements IAddressBook {

    @Autowired
    AddressBookRepository addressBookRepository;


    @Override
    public List<AddressBookDto> getAllContacts() {
        return addressBookRepository.findAll().stream()
                .map( contact -> new AddressBookDto(contact.getId(), contact.getName(), contact.getPhone()))
                .collect(Collectors.toList());
    }

    @Override
    public AddressBookDto saveContacts(AddressBookDto contact) {
        AddressBookEntity newEntity = new AddressBookEntity(contact.getId(), contact.getName(), contact.getPhone());

        addressBookRepository.save(newEntity);

        return new AddressBookDto(newEntity.getId(), newEntity.getName(), newEntity.getPhone());
    }

    @Override
    public AddressBookDto getContactById(Long id) {
        Optional<AddressBookEntity> contact = addressBookRepository.findById(id);
        return contact.map(c -> new AddressBookDto(c.getId(), c.getName(), c.getPhone()))
                .orElseThrow(() -> new RuntimeException("Address Book Not Found"));
    }

    @Override
    public AddressBookDto updateContact(Long id, AddressBookDto contact) {
        AddressBookEntity addFound = addressBookRepository.findById(id).orElseThrow(() -> new RuntimeException("Id Not Found"));
        addFound.setName(contact.getName());
        addFound.setPhone(contact.getPhone());
        addressBookRepository.save(addFound);
        return new AddressBookDto(addFound.getId(), addFound.getName(), addFound.getPhone());

    }

    @Override
    public String deleteContact(Long id) {
        AddressBookEntity addFound = addressBookRepository.findById(id).orElseThrow(() -> new RuntimeException("Id Not Found"));
        addressBookRepository.delete(addFound);

        return "Address Deleted";
    }
}
