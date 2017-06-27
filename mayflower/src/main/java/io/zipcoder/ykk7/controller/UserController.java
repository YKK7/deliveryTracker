package io.zipcoder.ykk7.controller;

import io.zipcoder.ykk7.entity.User;
import io.zipcoder.ykk7.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = {"http://localhost:8100","file://"})
    public ResponseEntity<List<User>> getAll() {
        LOG.info("getting all users");
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        if (users == null || users.isEmpty()){
            LOG.info("no users found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable("id") long id){
        LOG.info("getting user with id: {}", id);
        User user = userRepository.findOne(id);

        if (user == null){
            LOG.info("user with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody User user, UriComponentsBuilder ucBuilder){
        LOG.info("creating new user: {}", user);

        userRepository.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable long id, @RequestBody User user){
        LOG.info("updating user: {}", user);
        User currentUser = userRepository.findOne(id);

        if (currentUser == null){
            LOG.info("User with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentUser.setId(user.getId());
        currentUser.setName(user.getName());

        userRepository.save(user);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        LOG.info("deleting user with id: {}", id);
        User user = userRepository.findOne(id);

        if (user == null){
            LOG.info("Unable to delete. User with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
