package com.DiagramToDb.LMDToDB.Controller;

import com.DiagramToDb.LMDToDB.Model.Dto.UserDto;
import com.DiagramToDb.LMDToDB.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> Createuser(@RequestBody  UserDto user){
        return  userService.CreateUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody UserDto user){
        return  userService.login(user);
    }
    @PostMapping("/logout")
    public String Logout( @RequestHeader("Authorization") String token){
        return  userService.logout(token);
    }
    @PostMapping("/admin/{id}")
    public String admin(@PathVariable("id") Long id){
        return  userService.MakeAdmin(id);
    }
    @PostMapping("/sup/{id}")
    public String sup( @PathVariable("id") Long id){
        return  userService.Makesup(id);
    }
    @GetMapping("/user")
    public ResponseEntity<?> getuser(){
        return  userService.getcurrentuser();
    }
}
