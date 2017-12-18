package net.spring.security.filter.metadata;

import net.spring.security.filter.url.AntUrlPathMatcher;
import net.spring.security.filter.url.UrlMatcher;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.*;

public class InvocationSecurityMetadataSourceCustomizing implements FilterInvocationSecurityMetadataSource {
    private UrlMatcher urlMatcher = new AntUrlPathMatcher();
    private static Map<String, Collection<ConfigAttribute>> resourceMap;

    public InvocationSecurityMetadataSourceCustomizing() {
        loadResourceDefine();
    }

    // initial
    // load add url and permissions and roles when server initial started
    private void loadResourceDefine() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        Collection<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();

        ConfigAttribute _configAttribute0 = new SecurityConfig("ROLE_USER");
        attributes.add(_configAttribute0);
        resourceMap.put("/auth/index.jsp", attributes);
    }

    /**
     * object contains requests url
     */
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        // request url
        String url = filterInvocation.getRequestUrl();
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            if (urlMatcher.pathMatchesUrl(resURL, url)) {
                return resourceMap.get(resURL);
            }
        }
        // if not found, search again from database

        return null;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
}
