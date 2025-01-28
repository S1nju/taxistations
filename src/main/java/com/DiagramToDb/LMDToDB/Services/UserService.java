package com.DiagramToDb.LMDToDB.Services;

import com.DiagramToDb.LMDToDB.Model.Dto.UserDto;
import com.DiagramToDb.LMDToDB.Model.Entity.UserEntity;
import com.DiagramToDb.LMDToDB.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
  private  UserRepo userRepo;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private  TokenBlacklistService tokenBlacklistService;
    @Autowired
    private JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder(10);
    public ResponseEntity<?> CreateUser(UserDto user){
if(user.getUsername().isEmpty() || user.getPassword().isEmpty()|| user.getEmail().isEmpty()){
    return ResponseEntity.ok("failed to create user");  
}
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(UserEntity.toEntity(user));
        return ResponseEntity.ok(jwtService.generateToken(user.getUsername()));
    }
    public ResponseEntity<?> getcurrentuser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return ResponseEntity.ok(authentication);
        }
        return null;


    }
    public String MakeAdmin(Long id){
        Optional<UserEntity> l =userRepo.findById(id);
        if(l.isPresent()){
           l.get().setAuthorities(Collections.singleton(new  SimpleGrantedAuthority("ADMIN")));
            userRepo.save(l.get());
            return "Success";
        }else{
            return "User Not Found";
        }



    }
    public ResponseEntity<?> login(UserDto user){
if(user.getUsername()!=null) {
    Authentication authenticationManager = manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    if (authenticationManager.isAuthenticated()) {
        return ResponseEntity.ok(jwtService.generateToken(user.getUsername()));
    }
} else if(user.getEmail()!=null) {

    Authentication   authenticationManager = manager.authenticate(new UsernamePasswordAuthenticationToken( userRepo.findByEmail(user.getEmail()).getUsername(),user.getPassword()));
            if(authenticationManager.isAuthenticated()){

                return ResponseEntity.ok(jwtService.generateToken(userRepo.findByEmail(user.getEmail()).getUsername()));}else {
                throw new AuthenticationCredentialsNotFoundException("Wrong email or pass");}
        }
        return null;
    }

    public String logout(String token){
        String jwttoken = token.substring(7);
        tokenBlacklistService.addToBlacklist(jwttoken);
        return "Logged out Successfully";

    }

    public ResponseEntity<?> UpdateUser(UserDto user){
        userRepo.save(UserEntity.toEntity(user));
        return ResponseEntity.ok("Succces");
    }
    public ResponseEntity<?> Getusers(){

        return ResponseEntity.ok(userRepo.findAll());
    }
    public ResponseEntity<?> Getuser(Long Id){

        return ResponseEntity.ok( userRepo.findById(Id));
    }
    public ResponseEntity<?> GetUserByUsername(String Username){

        return ResponseEntity.ok(   userRepo.findByUsername(Username));
    }
    public ResponseEntity<?> Delete(Long Id){
        userRepo.deleteById(Id);
        return ResponseEntity.ok("Succces");
    }
}
