package com.example.AddressBook.Controller;

import com.example.AddressBook.Dto.AddressBookDto;
import com.example.AddressBook.Service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressBookApp {

    @Autowired
    AddressBookService addressBookService;


    @GetMapping("/AllContacts")
    public ResponseEntity<List<AddressBookDto>> getAllContacts(){
        return ResponseEntity.ok(addressBookService.getAllContacts());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AddressBookDto> getContactById(@PathVariable Long id){
        return ResponseEntity.ok(addressBookService.getContactById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<AddressBookDto> saveContacts(@RequestBody AddressBookDto dto){
        return ResponseEntity.ok(addressBookService.saveContacts(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressBookDto> updateContact(@PathVariable Long id, @RequestBody AddressBookDto dto){
        return ResponseEntity.ok(addressBookService.updateContact(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id){
        return ResponseEntity.ok(addressBookService.deleteContact(id));
    }

}
