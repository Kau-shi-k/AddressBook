package com.example.AddressBook.Interface;

import com.example.AddressBook.Dto.AuthUserDto;
import com.example.AddressBook.Dto.LoginDto;
import com.example.AddressBook.Entity.AuthUserEntity;

public interface IAuthUser {
    public AuthUserEntity register(AuthUserDto user);
    public String login(LoginDto user);
    public String forgotPassword(String email, String newPass);
    public String resetPassword(String email, String currentPass, String newPass);
}
