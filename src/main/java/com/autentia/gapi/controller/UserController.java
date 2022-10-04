package com.autentia.gapi.controller;

import com.autentia.gapi.domain.GapiUser;
import com.autentia.gapi.dto.UserOutDTO;
import com.autentia.gapi.repository.UserRepository;
import com.autentia.gapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/user")
public class UserController {


    @Autowired
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers(){

        return new ResponseEntity<List<GapiUser>>(userService.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username){
        GapiUser user = userService.findUserByUsername(username);
        if(user != null){
            return new ResponseEntity<GapiUser>(user, HttpStatus.OK);
        }
        return new ResponseEntity<String>("User not exist", HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        userService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> addUser(@RequestBody GapiUser user){
        userService.save(user);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public void loginUser(@RequestBody GapiUser user, HttpServletResponse response){
        userService.loadUserByUsername(user.getUsername());
    }

}
