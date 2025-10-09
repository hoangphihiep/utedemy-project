package vn.iotstar.controller;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GlobalFilterProject implements Filter {
	
	private static final SecureRandom secureRandom = new SecureRandom(); // tạo 1 đối tượng random tái sử dụng
    private static final Base64.Encoder base64Encoder = Base64.getEncoder();


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	
    	HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        String nonce;
            byte[] randomBytes = new byte[16];
            secureRandom.nextBytes(randomBytes);
            nonce = base64Encoder.encodeToString(randomBytes);
            System.out.println(nonce);
            httpReq.setAttribute("nonce", nonce);
            //  Chỉ set CSP header ở lần đầu (không set lại trong include/forward)
            httpResp.setHeader("Content-Security-Policy", buildCSP(nonce));
        
        //Missing Anti-clickjacking Header
        httpResp.setHeader("X-Frame-Options", "SAMEORIGIN");
        
        // Cross-Domain Misconfiguration
        httpResp.setHeader("Access-Control-Allow-Origin", "https://localhost:8443");
        httpResp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        httpResp.setHeader("Access-Control-Allow-Credentials", "true");

        // Chuyển tiếp request bình thường
        chain.doFilter(request, response);
    }
    private String buildCSP(String nonce) {
    	  String csp = "default-src 'self'; " +
    	            "script-src 'self' 'nonce-" + nonce + "' https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
    	            "style-src 'self' 'nonce-" + nonce + "' https://cdnjs.cloudflare.com https://cdn.jsdelivr.net https://code.jquery.com https://cdn.datatables.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
    	            "img-src 'self' data: https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
    	            "font-src 'self' https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
    	            "connect-src 'self' https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
    	            "frame-src https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
    	            "frame-ancestors 'self' https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
    	            "form-action 'self' https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net; " +
    	            "base-uri 'self' https://cdnjs.cloudflare.com https://code.jquery.com https://cdn.datatables.net https://cdn.jsdelivr.net https://cdn.ckeditor.com https://www.youtube.com https://via.placeholder.com https://photo.salekit.com https://unica.vn https://static.unica.vn https://upload.wikimedia.org https://static.wikia.nocookie.net;";
    	  return csp;

    }

}