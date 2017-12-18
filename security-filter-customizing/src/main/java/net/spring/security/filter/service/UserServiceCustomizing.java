package net.spring.security.filter.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserServiceCustomizing implements UserDetailsService {
    //  Roles
    private static String[] allRoles = new String[] {"ROLE_USER", "ROLE_ADMIN"};

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();

        if ("lolog".equals(username)) {
            // role
            for (String role: allRoles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role));
            }
        }
        else {
            grantedAuthorities.add(new SimpleGrantedAuthority(allRoles[1]));
        }

        return new User(username, username,true, true, true, true, grantedAuthorities);
    }
}

