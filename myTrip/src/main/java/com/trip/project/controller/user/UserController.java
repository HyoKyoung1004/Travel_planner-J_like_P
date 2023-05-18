package com.trip.project.controller.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trip.project.dto.user.UserDto;
import com.trip.project.service.user.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    
    @PostMapping("/join")
    public int join(@RequestBody UserDto user ) throws Exception{
    	
        System.out.println(user);
        return userService.join(user);        
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto user) throws Exception{
    	
        return ResponseEntity.ok().body(userService.login(user));        
    }
    
    @GetMapping("/viewMyPage/{userId}")
    public UserDto viewMypage(@PathVariable("userId") long userId,Principal pirPrincipal) throws Exception{
    	String op = pirPrincipal.getName();
    	System.out.println(op);
        return userService.viewMypage(userId);        
    }
    
    @DeleteMapping("delete/{userId}")
    public int deleteUser(@PathVariable("userId") long userId) throws Exception {
        return userService.deleteLike(userId);
    }
}