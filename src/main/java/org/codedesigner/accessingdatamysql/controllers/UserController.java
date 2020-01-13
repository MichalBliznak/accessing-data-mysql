package org.codedesigner.accessingdatamysql.controllers;

import org.codedesigner.accessingdatamysql.entities.User;
import org.codedesigner.accessingdatamysql.repositories.UserRepository;
import org.codedesigner.accessingdatamysql.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/user") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        try {
            logger.debug("Creating user {} with email {}.", name, email);
            userService.addUser(name, email);
            return "Saved";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    @PutMapping(path="/{id}")
    public @ResponseBody String updateUser(@PathVariable String id, @RequestParam String name, @RequestParam String email) {
        try {
            logger.debug("Updating user with id {}.", id);
            userService.updateUser(id, name, email);
            return "Updated";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        logger.debug("Getting all users.");
        return userService.getAllUsers();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody User getUsersById(@PathVariable String id) {
        // This returns a JSON or XML with the users
        logger.debug("Getting user with id {}.", id);
        return userService.getUser(id);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteUser(@PathVariable String id) {
        try {
            logger.debug("Deleting user with id {}.", id);
            userService.deleteUser(id);
            return "Deleted";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}