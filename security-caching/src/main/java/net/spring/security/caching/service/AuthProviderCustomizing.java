package net.spring.security.caching.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthProviderCustomizing implements AuthenticationProvider, InitializingBean {
    private Cache cache;
    private String cacheName;
    private CacheManager cacheManager;

    public void afterPropertiesSet() throws Exception {
        this.cache = this.cacheManager.getCache(this.cacheName);
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        String username = token.getName();

        // obtain user information from database
        UserDetails userDetails = cache.get(username, User.class);

        System.out.println(userDetails);

        return token;
    }

    public boolean supports(Class<?> authentication) {
        //返回true后,才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
