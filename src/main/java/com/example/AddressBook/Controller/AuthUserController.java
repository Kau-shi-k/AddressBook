package com.example.AddressBook.Controller;

import com.example.AddressBook.Dto.*;
import com.example.AddressBook.Interface.IAuthUser;
import com.example.AddressBook.Service.AuthUserService;
import com.example.AddressBook.Entity.AuthUserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")

public class AuthUserController {
    @Autowired
    IAuthUser authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@Valid @RequestBody AuthUserDto userDTO) throws Exception{
        AuthUserEntity user=authenticationService.register(userDTO);
        ResponseDto responseUserDTO =new ResponseDto("User details is submitted!",user);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginDto loginDTO){
        String result=authenticationService.login(loginDTO);
        ResponseDto responseUserDTO=new ResponseDto("Login successfully!!",result);
        return  new ResponseEntity<>(responseUserDTO,HttpStatus.OK);
    }
    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<ResponseDto> forgotPassword(@PathVariable String email,
                                                      @Valid @RequestBody ForgotPasswordDto forgotPasswordDTO) {
        String responseMessage = authenticationService.forgotPassword(email, forgotPasswordDTO.getPassword());
        ResponseDto responseDTO = new ResponseDto(responseMessage, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/resetPassword/{email}")
    public ResponseEntity<ResponseDto> resetPassword(@PathVariable String email,
                                                     @Valid @RequestBody ResetPasswordDto resetPasswordDTO) {
        String responseMessage = authenticationService.resetPassword(email,
                resetPasswordDTO.getCurrentPassword(),
                resetPasswordDTO.getNewPassword());
        return new ResponseEntity<>(new ResponseDto(responseMessage, null), HttpStatus.OK);
    }
}
