package net.spring.security.core.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CommonController {
    @ResponseBody
    @RequestMapping("/info.do")
    public Object info () throws IOException{
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @ResponseBody
    @RequestMapping("/logout.do")
    public Object logout (HttpServletRequest request, HttpServletResponse response) throws IOException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
