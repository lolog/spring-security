package net.spring.security.filter.filter;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * adding customizing datasource filter<br>
 *
 * <p>
 *     the filter will finish login by extends AbstractSecurityInterceptor class.<br>
 *     and which is located before FILTER_SECURITY_INTERCEPTOR
 * </p>
 *
 * <p>
 *     the filter depends on Security and Authentication, which is judge for user's GrantedAuthority<br>
 *     so which is located before  FILTER_SECURITY_INTERCEPTOR
 * </p>
 */
public class FilterSecurityInterceptorCustomizing extends AbstractSecurityInterceptor implements Filter {
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    // filter entrance
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(request, response, chain);
        invoke(filterInvocation);
    }

    public void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {
        // filterInvocation contains a request url.
        // and it's invoke method [-getAttributes(Object object)-] of MyInvocationSecurityMetadataSource class to get all permissions.
        // and then invoke method [-decide-] of MyAccessDecisionManager to judge permission
        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        try {
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    public void destroy() {

    }

    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }
}
