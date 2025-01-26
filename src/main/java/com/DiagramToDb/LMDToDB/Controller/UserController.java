package com.DiagramToDb.LMDToDB.Controller;

import com.DiagramToDb.LMDToDB.Model.Dto.UserDto;
import com.DiagramToDb.LMDToDB.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<?> Createuser(@RequestBody @Valid UserDto user){
        return  userService.CreateUser(user);
    }
    @PutMapping("/update")
    public ResponseEntity<?> UpdateUser(@RequestBody UserDto user){
        return  userService.UpdateUser(user);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> DeleteUser(@RequestParam Long id){
        return  userService.Delete(id);
    }
    @GetMapping("/users")
    public ResponseEntity<?> GetALLusers(){
        return  userService.Getusers();
    }
    @PostMapping("/makeadmin")
    public String makeadmin(@RequestParam Long id){
        return  userService.MakeAdmin(id);
    }
    @GetMapping("/user")
    public ResponseEntity<?> getuser(@RequestParam Long id){
        return  userService.Getuser(id);
    }
    @PostMapping("/userByname")
    public ResponseEntity<?> getuserByusername(@RequestParam String username){
        return  userService.GetUserByUsername(username);
    }
}
