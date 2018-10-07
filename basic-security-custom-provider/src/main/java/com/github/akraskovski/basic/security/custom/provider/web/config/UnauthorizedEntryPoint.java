package com.github.akraskovski.basic.security.custom.provider.web.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Default {@link org.springframework.security.web.AuthenticationEntryPoint} implementation for valid non-authorized response.
 */
@Component
public class UnauthorizedEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException e) throws IOException {
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + e.getMessage());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("MyRealm");
        super.afterPropertiesSet();
    }
}