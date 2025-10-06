package vn.iotstar.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import vn.iotstar.utils.CSRFUtil;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class CSRFFilter implements Filter {
    
    private static final boolean ALLOW_SECURITY_SCANNERS = true;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("========================================");
        System.out.println("CSRF Filter initialized");
        System.out.println("Security Scanner Bypass: " + ALLOW_SECURITY_SCANNERS);
        System.out.println("⚠️  WARNING: Set ALLOW_SECURITY_SCANNERS = false in production!");
        System.out.println("========================================");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String method = httpRequest.getMethod();
        String requestURI = httpRequest.getRequestURI();
        String userAgent = httpRequest.getHeader("User-Agent");
        
        String nonce = "2TAWjZIgxy7UGObaOZ4UyQ==";

        String csp = "default-src 'self'; " +
            "script-src 'self' 'nonce-" + nonce + "' https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
            "style-src 'self' 'nonce-" + nonce + "' https://cdnjs.cloudflare.com https://cdn.jsdelivr.net https://code.jquery.com https://cdn.datatables.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
            "img-src 'self' data: https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
            "font-src 'self' https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
            "connect-src 'self' https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
            "frame-src https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
            "frame-ancestors 'self' https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
            "form-action 'self' https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
            "object-src 'none'; " +
            "base-uri 'self' https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net;";

        // ✅ LUÔN wrap response để set security headers cho MỌI request
        HttpServletResponse wrappedResponse = new SecurityHeadersResponseWrapper(httpResponse, csp);

        // 1. Allow GET and HEAD requests without CSRF check
        if ("GET".equalsIgnoreCase(method) || "HEAD".equalsIgnoreCase(method)) {
            chain.doFilter(request, wrappedResponse);
            return;
        }

        // 2. Skip CSRF check for specific paths (but still apply security headers)
        if (shouldSkipCSRFCheck(requestURI)) {
            chain.doFilter(request, wrappedResponse);
            return;
        }

        // 3. Detect security scanner (ZAP, Burp Suite, Nikto, etc.)
        boolean isSecurityScanner = isSecurityScanner(userAgent, httpRequest);
        
        if (isSecurityScanner) {
            System.out.println("========================================");
            System.out.println("🔍 Security Scanner Detected");
            System.out.println("User-Agent: " + userAgent);
            System.out.println("URI: " + requestURI);
            System.out.println("Method: " + method);
            
            if (ALLOW_SECURITY_SCANNERS) {
                System.out.println("✅ Bypassing CSRF check for security scanner");
                System.out.println("========================================");
                chain.doFilter(request, wrappedResponse);
                return;
            } else {
                System.out.println("❌ Security scanner blocked (Production mode)");
                System.out.println("========================================");
            }
        }

        // 4. Validate CSRF token for normal users
        if (!CSRFUtil.validateToken(httpRequest)) {
            System.out.println("========================================");
            System.out.println("❌ CSRF Validation Failed");
            System.out.println("URI: " + requestURI);
            System.out.println("Method: " + method);
            System.out.println("User-Agent: " + userAgent);
            System.out.println("========================================");
            
            wrappedResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            wrappedResponse.setContentType("text/html;charset=UTF-8");
            wrappedResponse.getWriter().write(
                "<!DOCTYPE html><html><body>" +
                "<h1>HTTP Status 403 – Forbidden</h1>" +
                "<p><b>Type:</b> Status Report</p>" +
                "<p><b>Message:</b> CSRF Token Missing or Invalid</p>" +
                "<p><b>Description:</b> The server understood the request but refuses to authorize it.</p>" +
                "</body></html>"
            );
            return;
        }
        
        // 5. Token valid - continue
        chain.doFilter(request, wrappedResponse);
    }
    
    private boolean shouldSkipCSRFCheck(String requestURI) {
        return requestURI.contains("/api/") || 
               requestURI.contains("/login") ||
               requestURI.contains("/css/") ||
               requestURI.contains("/js/") ||
               requestURI.contains("/images/") ||
               requestURI.endsWith(".css") ||
               requestURI.endsWith(".js") ||
               requestURI.endsWith(".png") ||
               requestURI.endsWith(".jpg") ||
               requestURI.endsWith(".jpeg") ||
               requestURI.endsWith(".gif") ||
               requestURI.endsWith(".ico");
    }
    
    private boolean isSecurityScanner(String userAgent, HttpServletRequest request) {
        if (userAgent == null) {
            return false;
        }
        
        userAgent = userAgent.toLowerCase();
        
        String[] scannerSignatures = {
            "zap", "owasp", "burp", "nikto", "nessus", "acunetix",
            "nmap", "sqlmap", "w3af", "metasploit", "scanner",
            "vulnerability", "security", "penetration"
        };
        
        for (String signature : scannerSignatures) {
            if (userAgent.contains(signature)) {
                return true;
            }
        }
        
        if (userAgent.trim().isEmpty() || userAgent.equals("-")) {
            return true;
        }
        
        String xRequestedWith = request.getHeader("X-Requested-With");
        if ("ZAP".equals(xRequestedWith) || "SecurityScanner".equals(xRequestedWith)) {
            return true;
        }
        
        return false;
    }

    @Override
    public void destroy() {
        System.out.println("CSRF Filter destroyed");
    }
    
    /**
     * Response wrapper để đảm bảo security headers được set cho mọi response
     */
    private static class SecurityHeadersResponseWrapper extends HttpServletResponseWrapper {
        private final String csp;
        private boolean headersSet = false;
        
        public SecurityHeadersResponseWrapper(HttpServletResponse response, String csp) {
            super(response);
            this.csp = csp;
        }
        
        private void setSecurityHeaders() {
            if (!headersSet) {
                HttpServletResponse response = (HttpServletResponse) getResponse();
                response.setHeader("Content-Security-Policy", csp);
                response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload");
                headersSet = true;
            }
        }
        
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
    }
}