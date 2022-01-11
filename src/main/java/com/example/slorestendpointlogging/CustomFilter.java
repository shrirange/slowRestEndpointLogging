package com.example.slorestendpointlogging;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFilter.class);
    
    @Value(value="${slowPerformingRestEndPoints}")
    Long slowPerformingRestEndPoints;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("########## Initiating Custom filter ##########");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        long startTime = System.currentTimeMillis();

        //call next filter in the filter chain
        filterChain.doFilter(request, response);
        
        long endTime = System.currentTimeMillis();

        long difference =  endTime - startTime;
        if(difference > slowPerformingRestEndPoints)
        LOGGER.info("The end point " +  request.getRequestURI() + " slow and took " + difference);
    }

    @Override
    public void destroy() {
       
    }
}