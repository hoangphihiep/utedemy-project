package vn.iotstar.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class SecureResponseWrapper extends HttpServletResponseWrapper {

    private final String csp;
    private boolean headersSet = false;

    public SecureResponseWrapper(HttpServletResponse response, String csp) {
        super(response);
        this.csp = csp;
    }

    // =========================
    // 1️⃣ COOKIE HARDENING
    // =========================
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

        // ép các flag bảo mật
        sb.append("; HttpOnly; Secure; SameSite=Strict");

        super.addHeader("Set-Cookie", sb.toString());
    }

    @Override
    public void addHeader(String name, String value) {
        if ("Set-Cookie".equalsIgnoreCase(name) && value.startsWith("JSESSIONID")) {
            value = appendCookieFlags(value);
        }
        super.addHeader(name, value);
    }

    @Override
    public void setHeader(String name, String value) {
        if ("Set-Cookie".equalsIgnoreCase(name) && value.startsWith("JSESSIONID")) {
            value = appendCookieFlags(value);
        }
        super.setHeader(name, value);
    }

    private String appendCookieFlags(String value) {
        String lower = value.toLowerCase();
        if (!lower.contains("samesite")) {
            value += "; SameSite=Strict";
        }
        if (!lower.contains("secure")) {
            value += "; Secure";
        }
        if (!lower.contains("httponly")) {
            value += "; HttpOnly";
        }
        return value;
    }

    // =========================
    // 2️⃣ SECURITY HEADERS
    // =========================
    private void setSecurityHeaders() {
        if (!headersSet) {
            HttpServletResponse response = (HttpServletResponse) getResponse();

            // CSP
            response.setHeader("Content-Security-Policy", csp);

            // HSTS
            response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload");

            // Clickjacking protection
            response.setHeader("X-Frame-Options", "SAMEORIGIN");

            // MIME sniffing
            response.setHeader("X-Content-Type-Options", "nosniff");

            // CORS configuration
            response.setHeader("Access-Control-Allow-Origin", "https://localhost:8443");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
            response.setHeader("Access-Control-Allow-Credentials", "true");

            headersSet = true;
        }
    }

    // =========================
    // 3️⃣ ENSURE HEADERS ON RESPONSE
    // =========================
    @Override
    public void sendError(int sc) throws IOException {
        setSecurityHeaders();
        super.sendError(sc);
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        setSecurityHeaders();
        super.sendError(sc, msg);
    }

    @Override
    public void sendRedirect(String location) throws IOException {
        setSecurityHeaders();
        super.sendRedirect(location);
    }

    @Override
    public void setStatus(int sc) {
        setSecurityHeaders();
        super.setStatus(sc);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        setSecurityHeaders();
        return super.getOutputStream();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        setSecurityHeaders();
        return super.getWriter();
    }

    @Override
    public void flushBuffer() throws IOException {
        setSecurityHeaders();
        super.flushBuffer();
    }

    @Override
    public void resetBuffer() {
        setSecurityHeaders();
        super.resetBuffer();
    }

    @Override
    public void reset() {
        setSecurityHeaders();
        super.reset();
    }

    @Override
    public void setLocale(Locale loc) {
        setSecurityHeaders();
        super.setLocale(loc);
    }
}
