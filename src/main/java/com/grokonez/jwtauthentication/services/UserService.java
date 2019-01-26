package com.grokonez.jwtauthentication.services;

import com.grokonez.jwtauthentication.model.Role;
import com.grokonez.jwtauthentication.model.RoleName;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.RoleRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository rep;


    @Autowired
    private RoleRepository roleRepository;

    public User currentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return this.rep.findByUsername(username).get();
    }

    public User findById(long id){
        return this.rep.findById(id).get();
    }

    public User save(User user){
        return this.rep.save(user);
    }

    public boolean hasPm(User user){
        Set<Role> roles = user.getRoles();
        Role role = this.roleRepository.findByName(RoleName.ROLE_PM).get();
        return roles.contains(role);
    }

    public boolean hasAdmin(User user){
        Set<Role> roles = user.getRoles();
        Role role = this.roleRepository.findByName(RoleName.ROLE_ADMIN).get();
        return roles.contains(role);
    }

}
