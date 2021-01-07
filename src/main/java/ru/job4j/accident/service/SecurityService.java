package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.UserRepository;

@Service
public class SecurityService {
    private final UserRepository users;
    private final AuthorityRepository authorities;

    @Autowired
    public SecurityService(UserRepository users, AuthorityRepository authorities) {
        this.users = users;
        this.authorities = authorities;
    }

    public void save(User user) {
        users.save(user);
    }

    public Authority findByAuthority(String role) {
        return authorities.findByAuthority(role);
    }
}
