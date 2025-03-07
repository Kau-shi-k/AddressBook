package com.example.AddressBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.AddressBook")
public class AddressBookProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressBookProjectApplication.class, args);
	}

}
