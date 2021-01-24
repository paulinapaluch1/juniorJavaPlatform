package com.pai.project.junior;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class JuniorAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            List<String> loggedUserRoles = ((UserPrincipal) principal).getRoleList();

            if (loggedUserRoles.contains("ADMIN")) {
                httpServletResponse.sendRedirect("/");
            } else if (loggedUserRoles.contains("STUDENT")) {
                httpServletResponse.sendRedirect("/student/profile");
            } else if (loggedUserRoles.contains("MENTOR")) {
                httpServletResponse.sendRedirect("/mentors/profile");
            } else
                httpServletResponse.sendRedirect("/error");
        } else
            httpServletResponse.sendRedirect("/error");
    }
}
