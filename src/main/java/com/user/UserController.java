package com.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") long id){
        return new ResponseEntity<User>(userService.getUser(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable long id){
        userService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
