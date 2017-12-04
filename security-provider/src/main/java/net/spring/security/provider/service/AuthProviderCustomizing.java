package net.spring.security.provider.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthProviderCustomizing implements AuthenticationProvider{
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        String username = token.getName();

        // obtain user information from database

        if (!"password".equals(token.getCredentials())) {
            throw new BadCredentialsException("Invalid Passwoed");
        }
        // UserDetails userDetails = Use
        System.out.println(authentication.getDetails());
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getCredentials());
        return token;
    }

    public boolean supports(Class<?> authentication) {
        //返回true后,才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
