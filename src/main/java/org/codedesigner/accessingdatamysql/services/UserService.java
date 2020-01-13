package org.codedesigner.accessingdatamysql.services;

import org.codedesigner.accessingdatamysql.entities.User;
import org.codedesigner.accessingdatamysql.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(String name, String email) {
        User n = createUser(name, email);
        userRepository.save(n);
        return n;
    }

    @Cacheable(value = "users", key = "#id")
    public User getUser(String id) {
        return userRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(String id) {
        userRepository.deleteById(Integer.parseInt(id));
    }

    @CachePut(value = "users", key = "#id")
    public User updateUser(String id, String name, String email) {
        User n = createUser(name, email);
        n.setId(Integer.parseInt(id));
        userRepository.save(n);
        return n;
    }

    private User createUser(String name, String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        return n;
    }
}
