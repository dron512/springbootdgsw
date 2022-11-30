package com.example.demo.config;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
public class ApiCheckFilter  extends OncePerRequestFilter {

    private AntPathMatcher antPathMatcher;
    private String patten;

    @Autowired
    JwtUtil jwtUtil;
    public ApiCheckFilter(String patten) {
        antPathMatcher = new AntPathMatcher();
        this.patten = patten;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("일단 일로 오나"+patten);
        System.out.println(request.getRequestURI());
        System.out.println(antPathMatcher.match(patten,request.getRequestURI()));

        if(antPathMatcher.match(patten,request.getRequestURI())) {
            System.out.println("true");

            boolean result = checkAuthHeader(request);
            System.out.println(result);

            if(result) {
                System.out.println("필더링됨");
            }
            else{
                try {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    // json 리턴
                    response.setContentType("application/json;charset=utf-8");
                    JSONObject json = new JSONObject();
                    String message = "FAIL CHECK API TOKEN";
                    json.put("code", "403");
                    json.put("message", message);

                    PrintWriter out = response.getWriter();
                    out.print(json);
                    return;
                }catch (Exception e){
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkAuthHeader(HttpServletRequest request) {

        boolean checkResult = false;
        log.info("checkauthHeader");

        String authHeader = request.getHeader("Authorization");
        log.info("before Authorization exist: " + authHeader);
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            log.info("after Authorization exist: " + authHeader);

            try {
                String email = jwtUtil.validateAndExtract(authHeader.substring(7));
                log.info("validate result: " + email);
                checkResult =  email.length() > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return checkResult;
    }
}
