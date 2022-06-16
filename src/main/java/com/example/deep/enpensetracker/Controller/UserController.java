package com.example.deep.enpensetracker.Controller;

import com.example.deep.enpensetracker.Entity.User;
import com.example.deep.enpensetracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping("users/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        return new ResponseEntity<User>(userService.read(id),HttpStatus.OK);
    }
    @PutMapping("users/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id){
        User mUser = userService.update(user,id);
        return new ResponseEntity<User>(mUser, HttpStatus.OK);
    }
    @DeleteMapping("/users/{id}")
    public void deleteExpenseById(@PathVariable Long id){

        userService.delete(id);
    }
}
