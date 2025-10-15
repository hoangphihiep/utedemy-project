package vn.iotstar.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.Base64;

public class CSRFUtil {
    private static final String CSRF_TOKEN_ATTR = "csrfToken";
    private static final int TOKEN_LENGTH = 32;
    private static final int MAX_TOKEN_LENGTH = 100;
    
    // Độ dài token chuẩn sau khi encode Base64
    private static final int EXPECTED_TOKEN_LENGTH = 44; // 32 bytes -> ~44 chars Base64

    /**
     * Generate CSRF token với validation
     */
    public static String generateToken(HttpServletRequest request) {
        HttpSession session = request.getSession();

        SecureRandom random = new SecureRandom();
        byte[] tokenBytes = new byte[TOKEN_LENGTH];
        random.nextBytes(tokenBytes);
        String token = Base64.getEncoder().encodeToString(tokenBytes);
        
        // Validate token length ngay sau khi generate
        if (token.length() > MAX_TOKEN_LENGTH) {
            System.err.println("Generated token too long: " + token.length());
            // Truncate nếu quá dài (không nên xảy ra với Base64)
            token = token.substring(0, MAX_TOKEN_LENGTH);
        }
        
        session.setAttribute(CSRF_TOKEN_ATTR, token);
        
        return token;
    }

    /**
     * Get CSRF token từ session
     */
    public static String getToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String token = (String) session.getAttribute(CSRF_TOKEN_ATTR);
            
            // Validate token từ session
            if (token != null && token.length() > MAX_TOKEN_LENGTH) {
                System.err.println("Token in session too long: " + token.length());
                // Remove invalid token
                session.removeAttribute(CSRF_TOKEN_ATTR);
                return null;
            }
            
            return token;
        }
        return null;
    }

    /**
     * Validate CSRF token với multiple security checks
     */
    public static boolean validateToken(HttpServletRequest request) {
        String sessionToken = getToken(request);
        String requestToken = request.getParameter("csrfToken");
        
        // Check 1: Both tokens must exist
        if (sessionToken == null || requestToken == null) {
            System.err.println("CSRF validation failed: Missing token");
            return false;
        }
        
        // Check 2: Validate length BEFORE any processing
        if (requestToken.length() > MAX_TOKEN_LENGTH) {
            System.err.println("CSRF token too long: " + requestToken.length() + 
                " characters. Potential attack detected.");
            return false;
        }
        
        // Check 3: Validate length is reasonable (not too short)
        if (requestToken.length() < 20) {
            System.err.println("CSRF token too short: " + requestToken.length() + 
                " characters. Potential attack detected.");
            return false;
        }
        
        // Check 4: Validate token format (Base64 characters only)
        if (!isValidBase64(requestToken)) {
            System.err.println("CSRF token contains invalid characters. Potential attack detected.");
            return false;
        }
        // Check 5: Compare tokens (constant-time comparison to prevent timing attacks)
        if (!constantTimeEquals(sessionToken, requestToken)) {
            System.err.println("CSRF token mismatch");
            return false;
        }
        return true;
    }
    
    /**
     * Constant-time string comparison để ngăn timing attacks
     */
    private static boolean constantTimeEquals(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        
        int result = 0;
        for (int i = 0; i < a.length(); i++) {
            result |= a.charAt(i) ^ b.charAt(i);
        }
        
        return result == 0;
    }
    
    /**
     * Validate Base64 format
     */
    private static boolean isValidBase64(String token) {
        // Base64 chỉ chứa: A-Z, a-z, 0-9, +, /, =
        return token.matches("^[A-Za-z0-9+/=]+$");
    }

    /**
     * Remove CSRF token từ session
     */
    public static void removeToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(CSRF_TOKEN_ATTR);
        }
    }
    
    /**
     * Regenerate token (dùng sau khi login/logout)
     */
    public static String regenerateToken(HttpServletRequest request) {
        removeToken(request);
        return generateToken(request);
    }
}
