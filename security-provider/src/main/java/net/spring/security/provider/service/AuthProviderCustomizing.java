package net.spring.security.provider.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class AuthProviderCustomizing implements AuthenticationProvider{
    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;

    //  Roles
    private static String[] allRoles = new String[] {"ROLE_USER", "ROLE_ADMIN"};

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        String username = token.getName();

        UserDetails user = queryLoad(username);
        // obtain user information from database
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return token;
    }

    public boolean supports(Class<?> authentication) {
        //返回true后,才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

    public UserDetails queryLoad(String username) {
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
            return null;
        }

        return new User(username, username, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }
}
