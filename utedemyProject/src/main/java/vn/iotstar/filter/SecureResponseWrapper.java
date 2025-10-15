package vn.iotstar.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class SecureResponseWrapper extends HttpServletResponseWrapper {

  
    private boolean headersSet = false;
    private final String csp;

    public SecureResponseWrapper(HttpServletResponse response, String csp) {
        super(response);
        this.csp = csp;
 
    }
   

    // =========================
    // 2️⃣ SECURITY HEADERS
    // =========================
    private void setSecurityHeaders() {
        if (!headersSet) {
            HttpServletResponse response = (HttpServletResponse) getResponse();
    
            //CSP
//            response.setHeader("Content-Security-Policy", csp);
            
            // Clickjacking protection
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            
          //  response.setHeader("X-Content-Type-Options", "nosniff");

          //  response.setHeader("Referrer-Policy", "no-referrer-when-downgrade");
            	
           // response.setHeader("Permissions-Policy", "geolocation=(), microphone=()");

           // response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, private");

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