package net.spring.security.provider.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class AuthServiceCustomizing implements UserDetailsService {
    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;

    //  Roles
    private static String[] allRoles = new String[] {"ROLE_USER", "ROLE_ADMIN"};

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();

        if ("lolog".equalsIgnoreCase(username)) {
            for (String role: allRoles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role));
            }
        }
        else if ("user".equalsIgnoreCase(username)){
            grantedAuthorities.add(new SimpleGrantedAuthority(allRoles[0]));
        }
        else {

        }

        String password = username + "_0";

        return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }
}

