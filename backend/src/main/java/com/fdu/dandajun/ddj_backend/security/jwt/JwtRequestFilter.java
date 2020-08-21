package com.fdu.dandajun.ddj_backend.security.jwt;

import fudan.se.lab2.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Yu Zhexuan
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String token_header;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String auth_token = request.getHeader(this.token_header);
        String auth_token_start = "Bearer ";

        if (request.getMethod().equals("OPTIONS")) {
            filterChain.doFilter(request, response);
        }

        if (!request.getRequestURI().equals("/meeting")) {
            filterChain.doFilter(request, response);
        } else if (auth_token != null && !auth_token.isEmpty() && auth_token.startsWith(auth_token_start)) {
            auth_token = auth_token.substring(auth_token_start.length());
            try {
                String username = jwtTokenUtil.getUsernameFromToken(auth_token);
                UserDetails user = jwtUserDetailsService.loadUserByUsername(username);

                logger.info(String.format("Checking authentication for user %s.", username));

                if (!jwtTokenUtil.validateToken(auth_token, user)) {
                    response_401(response);
                } else {
                    logger.info(String.format("Authenticated user %s, setting security context", username));

                    filterChain.doFilter(request, response);
                }

            } catch (UsernameNotFoundException | ExpiredJwtException ex) {
                response_401(response);
            }
        } else {
            response_401(response);
        }
    }

    private void response_401(HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        response.setStatus(200);
        try {
            data.put("error", 401);
        } catch (JSONException ex) {
            ex.printStackTrace();
            logger.debug(ex);
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.print(data);
        printWriter.flush();
        printWriter.close();
    }
}

