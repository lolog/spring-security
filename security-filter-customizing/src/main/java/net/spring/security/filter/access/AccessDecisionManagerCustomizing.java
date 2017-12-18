package net.spring.security.filter.access;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

public class AccessDecisionManagerCustomizing implements AccessDecisionManager {
    // check permission of request url
    // authentication get from SecurityContextHolder, which contains user's permission
    // object contains url
    // configAttributes is demanded permissions
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null) {
            return;
        }

        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while(iterator.hasNext()) {
            ConfigAttribute attribute = iterator.next();
            SecurityConfig securityConfig = (SecurityConfig) attribute;
            String role = securityConfig.getAttribute();

            for(GrantedAuthority grantedAuthority: authentication.getAuthorities()) {
                if(role.equalsIgnoreCase(grantedAuthority.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("Not Access");
    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
}
