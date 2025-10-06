package vn.iotstar.controller;

import jakarta.servlet.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;


public class SameSiteCookieFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResp = (HttpServletResponse) response;

        HttpServletResponseWrapper wrappedResp = new HttpServletResponseWrapper(httpResp) {

        	@Override
        	public void addCookie(Cookie cookie) {
        	    StringBuilder sb = new StringBuilder();
        	    sb.append(cookie.getName()).append("=").append(cookie.getValue());

        	    if (cookie.getPath() != null) {
        	        sb.append("; Path=").append(cookie.getPath());
        	    }
        	    if (cookie.getDomain() != null) {
        	        sb.append("; Domain=").append(cookie.getDomain());
        	    }

        	    // ép flag
        	    sb.append("; HttpOnly; Secure; SameSite=Strict");

        	    super.addHeader("Set-Cookie", sb.toString());
        	}


            @Override
            public void addHeader(String name, String value) {
                if ("Set-Cookie".equalsIgnoreCase(name) && value.startsWith("JSESSIONID")) {
                    value = appendFlags(value);
                }
                super.addHeader(name, value);
            }

            @Override
            public void setHeader(String name, String value) {
                if ("Set-Cookie".equalsIgnoreCase(name) && value.startsWith("JSESSIONID")) {
                    value = appendFlags(value);
                }
                super.setHeader(name, value);
            }

            private String appendFlags(String value) {
                if (!value.toLowerCase().contains("samesite")) {
                    value += "; SameSite=Strict";
                }
                if (!value.toLowerCase().contains("secure")) {
                    value += "; Secure";
                }
                if (!value.toLowerCase().contains("httponly")) {
                    value += "; HttpOnly";
                }
                return value;
            }
        };

        chain.doFilter(request, wrappedResp);
    }
}
