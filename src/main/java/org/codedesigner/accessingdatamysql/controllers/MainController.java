package org.codedesigner.accessingdatamysql.controllers;

import org.codedesigner.accessingdatamysql.entities.User;
import org.codedesigner.accessingdatamysql.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        try {
            logger.debug("Creating user {} with email {}.", name, email);

            User n = new User();
            n.setName(name);
            n.setEmail(email);
            userRepository.save(n);

            logger.debug("Done with creating a new user.");
            return "Saved";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        logger.debug("Getting all users.");
        return userRepository.findAll();
    }
}