package net.spring.security.caching.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class AuthServiceCustomizing implements UserDetailsService, InitializingBean {
    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;

    private Cache cache;
    private String cacheName;
    private CacheManager cacheManager;

    private static AtomicBoolean hasInitial = new AtomicBoolean(false);

    public void afterPropertiesSet() throws Exception {
        this.cache = this.cacheManager.getCache(this.cacheName);
    }

    //  Roles
    private static String[] allRoles = new String[] {"ROLE_USER", "ROLE_ADMIN"};

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();

        UserDetails userDetails = null;
        if (cache.get(username) == null) {
            cache.clear();
            userDetails = loadDetails(username);
        }
        else {
            userDetails = cache.get(username, User.class);
        }

        if (userDetails == null) {
            throw new UsernameNotFoundException(username + " Not Found");
        }

        return new User(username, userDetails.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, userDetails.getAuthorities());
    }

    private UserDetails loadDetails(String userName) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (String role: allRoles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        UserDetails userDetails = null;
        for (int i=0; i<10; i++) {
            User user = new User("user_" + i, "password_" + i,
                    enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                    grantedAuthorities);
            cache.put("user_" + i, user);

            if (userName.equalsIgnoreCase("user_" + i)) {
                userDetails = user;
            }
        }
        return userDetails;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}

