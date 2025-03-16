package com.example.AddressBook.Service;

import com.example.AddressBook.Dto.AuthUserDto;
import com.example.AddressBook.Dto.LoginDto;
import com.example.AddressBook.Entity.AuthUserEntity;
import com.example.AddressBook.Exception.UserNotFoundException;
import com.example.AddressBook.Interface.IAuthUser;
import com.example.AddressBook.Repository.AuthUserRepository;
import com.example.AddressBook.Util.EmailSenderService;
import com.example.AddressBook.Util.Jwttoken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthUserService implements IAuthUser {

    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    Jwttoken jwttoken;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AuthUserEntity register(AuthUserDto userDto) {
        try{
            log.info("Registering new User: {}", userDto.getEmail());
            AuthUserEntity user = new AuthUserEntity(userDto);

            String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
            user.setPassword(encryptedPassword);

            String token = jwttoken.createToken(user.getId());

            authUserRepository.save(user);

            emailSenderService.sendMail(
                    user.getMail(),
                    "Registers in Address Book",
                    "Hi " + user.getFirstName() + ",\nYou have been successfully registered!\n\nYour registered details are:\n\n"
                            + "User Id: " + user.getId() + "\n"
                            + "First Name: " + user.getFirstName() + "\n"
                            + "Last Name: " + user.getLastName() + "\n"
            );

            log.info("User Registerd Succesfully ", user.getMail());
            return user;
        } catch(Exception e){
            log.error("Error Occured while registering User: {}", e.getMessage());
            throw new UserNotFoundException("Registration Failed due to Error, Please Try Again");
        }
    }

    @Override
    public String login(LoginDto user) {
        try{
            log.info("Login Attempt for email: {}", user.getEmail());
            Optional<AuthUserEntity> userEntity = Optional.ofNullable(authUserRepository.findByToken(user.getMail()));
            if(userEntity.isPresent()){
                if (passwordEncoder.matches(user.getPassword(), userEntity.get().getPassword())) {
                    log.info("Login successful for user: {}", userEntity.get().getMail());
                    emailSenderService.sendMail(
                            userEntity.get().getMail(),
                            "Logged in Successfully!",
                            "Hi " + userEntity.get().getFirstName() + ",\n\nYou have successfully logged in into Greeting App!");

                    return "Congratulations!! You have logged in successfully!";
                } else {
                    log.warn("Login failed: Incorrect password for email: {}", user.getMail());
                    throw new UserNotFoundException("Sorry! Email or Password is incorrect!");
                }
            } else {
                log.warn("Login failed: No user found for email: {}", user.getMail());
                throw new UserNotFoundException("Sorry! Email or Password is incorrect!");
            }

        } catch (Exception e) {
            log.error("Error during login process: {}", e.getMessage());
            throw new UserNotFoundException("Login failed due to an internal error. Please try again.");
        }
    }

    @Override
    public String forgotPassword(String email, String newPass) {
        try {
            log.info("Processing forgot password request for email: {}", email);
            AuthUserEntity user = authUserRepository.findByEmail(email);

            if (user == null) {
                log.warn("Forgot password request failed: No user found for email: {}", email);
                throw new UserNotFoundException("Sorry! We cannot find the user email: " + email);
            }

            String encryptedPassword = passwordEncoder.encode(newPass);
            user.setPassword(encryptedPassword);
            authUserRepository.save(user);

            emailSenderService.sendMail(
                    user.getMail(),
                    "Password Forget Updation Successful",
                    "Hi " + user.getFirstName() + ",\n\nYour password has been successfully changed!");

            log.info("Password updated successfully for email: {}", email);

            return "Password has been changed successfully!";
        } catch (Exception e) {
            log.error("Error during forgot password process: {}", e.getMessage());
            throw new UserNotFoundException("Error occurred while updating password. Please try again.");
        }
    }

    @Override
    public String resetPassword(String email, String currentPass, String newPass) {
        try {
            log.info("Resetting password for email: {}", email);
            AuthUserEntity user = authUserRepository.findByEmail(email);

            if (user == null) {
                log.warn("Password reset failed: No user found for email: {}", email);
                throw new UserNotFoundException("User not found with email: " + email);
            }

            if (!passwordEncoder.matches(currentPass, user.getPassword())) {
                log.warn("Password reset failed: Incorrect current password for email: {}", email);
                throw new UserNotFoundException("Current password is incorrect!");
            }

            String encryptedPassword = passwordEncoder.encode(newPass);
            user.setPassword(encryptedPassword);
            authUserRepository.save(user);

            emailSenderService.sendMail(
                    user.getMail(),
                    "Password Reset Successful",
                    "Hi " + user.getFirstName() + ",\n\nYour password has been successfully updated!");

            log.info("Password reset successful for email: {}", email);

            return "Password reset successfully!";
        } catch (Exception e) {
            log.error("Error during password reset process: {}", e.getMessage());
            throw new UserNotFoundException("Error occurred while resetting password. Please try again.");
        }
    }

}
